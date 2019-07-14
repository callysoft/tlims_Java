package com.tlimskech.marketplace.global.contact;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactRepository.save(contact));
    }

    @PostMapping("findAll")
    public ResponseEntity<?> create(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(findAll(searchRequest));
    }

    private Page<Contact> findAll(SearchRequest request) {
        QContact qContact = QContact.contact;
        PageRequest pageRequest = PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort());
        if (ObjectUtils.isEmpty(request.getSearchTerm())) {
            return contactRepository.findAll(qContact.isNotNull(), pageRequest);
        }
        BooleanExpression expression = qContact.email.containsIgnoreCase(request.getSearchTerm())
                .or(qContact.phoneNumber.containsIgnoreCase(request.getSearchTerm()))
                .or(qContact.name.containsIgnoreCase(request.getSearchTerm()));
        return contactRepository.findAll(expression, pageRequest);
    }
}
