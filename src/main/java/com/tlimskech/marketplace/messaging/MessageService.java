package com.tlimskech.marketplace.messaging;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.auth.user.User;
import com.tlimskech.marketplace.auth.user.UserService;
import com.tlimskech.marketplace.core.data.CodeValue;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import com.tlimskech.marketplace.core.service.GlobalService;
import com.tlimskech.marketplace.global.contact.Contact;
import com.tlimskech.marketplace.global.contact.ContactRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.tlimskech.marketplace.messaging.MessageSource.EXTERNAL;
import static com.tlimskech.marketplace.messaging.MessageSource.INTERNAL;

@Service
public class MessageService extends GlobalService implements BaseService<Message, Long> {

    private MessageRepository messageRepository;
    private UserService userService;
    private AdService adService;
    private ContactRepository contactRepository;

    public MessageService(MessageRepository messageRepository, UserService userService, AdService adService, ContactRepository contactRepository) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.adService = adService;
        this.contactRepository = contactRepository;
    }

    @Override
    public Message create(Message message) {
        message.setSource(EXTERNAL);
        this.setPostDetails(message);
        if (!StringUtils.isEmpty(message.getContact().getEmail())) {
            Optional<User> user = userService.findByUsername(message.getContact().getEmail());
            message.setSource(user.isPresent() ? INTERNAL : EXTERNAL);
        }
        Contact contact = createContact(message.getContact());
        message.setContact(contact);
        System.out.println(message.toXmlString());
        return this.messageRepository.save(message);
    }

    private void setPostDetails(Message message) {
        if (!ObjectUtils.isEmpty(message.getPostId())) {
            Optional<Ad> ad = adService.findById(message.getPostId());
            ad.ifPresent(ad1 -> {
                message.setPost(CodeValue.builder().code(String.valueOf(ad1.getId())).name(ad1.getTitleDescription().getTitle()).build());
                message.setPostCode(ad.get().getCode());
            });
        }
    }

    @Override
    public Message update(Message message) {
        return null;
    }

    @Override
    public void delete(Message message) {
        this.messageRepository.delete(message);
    }

    @Override
    public Page<Message> findAll(Message message, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Message> findById(Long id) {
        return this.messageRepository.findById(id);
    }

    public Page<Message> findUserMessages(SearchRequest request) {
        return messageRepository.findByRecipient(UserService.getCurrentUser(), PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort()));
    }
}
