package com.tlimskech.marketplace.ad;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.auth.user.User;
import com.tlimskech.marketplace.auth.user.UserService;
import com.tlimskech.marketplace.core.data.*;
import com.tlimskech.marketplace.core.service.BaseService;
import com.tlimskech.marketplace.core.service.GlobalService;
import com.tlimskech.marketplace.exception.ApplicationException;
import com.tlimskech.marketplace.global.contact.Contact;
import com.tlimskech.marketplace.notification.Notification;
import com.tlimskech.marketplace.notification.NotificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Boolean.FALSE;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class AdService extends GlobalService implements BaseService<Ad, Long> {

    private final AdRepository adRepository;
    @PersistenceContext
    private EntityManager em;
    private UserService userService;
    private NotificationService notificationService;

    public AdService(AdRepository adRepository, UserService userService, NotificationService notificationService) {
        this.adRepository = adRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @Override
    public Ad create(Ad ad) {
        ad.setNegotiable(!isEmpty(ad.getNegotiable()) ? ad.getNegotiable() : FALSE);
        ad.setAuthorized(!isEmpty(ad.getAuthorized()) ? ad.getAuthorized() : FALSE);
        ad.setFeatured(!isEmpty(ad.getFeatured()) ? ad.getFeatured() : FALSE);
        ad.setArchived(!isEmpty(ad.getArchived()) ? ad.getArchived() : FALSE);
        ad.setSponsored(!isEmpty(ad.getSponsored()) ? ad.getSponsored() : FALSE);
        persistContact(ad);
        return adRepository.save(ad);
    }

    private void persistContact(Ad ad) {
        Optional<User> user = userService.findByUsername(UserService.getCurrentUser());
        user.ifPresent(user1 -> {
            String name = !StringUtils.isEmpty(user1.getDisplayName()) ? user1.getDisplayName() : String.format("%s %s", user1.getLastName(), user1.getFirstName());
            String phoneNumber = !StringUtils.isEmpty(ad.getContact().getPhoneNumber()) ? ad.getContact().getPhoneNumber() : user1.getPhoneNumber();
            Contact contact = Contact.builder().email(user1.getEmail()).phoneNumber(phoneNumber).name(name).build();
            System.out.println(contact.toXmlString());
            contact = createContact(contact);
            ad.setContact(contact);
            ad.setPrimaryContact(ContactDto.copyFromContact(contact));
        });
    }

    @Override
    public Ad update(Ad ad) {
        Ad found = adRepository.findById(ad.getId()).orElseThrow(() -> new ApplicationException("Resource not found"));
        found.copyForUpdate(ad);
        persistContact(ad);
        return adRepository.save(found);
    }

    @Override
    public void delete(Ad ad) {

    }

    public void approve(Ad ad) {
        Ad ad1 = this.findById(ad.getId()).orElseThrow(() -> new ApplicationException("Resource not found"));
        ad1.setAuthorized(Boolean.TRUE);
        ad1.setPrimaryContact(ad.getPrimaryContact());
        this.adRepository.save(ad1);
        emailUser(ad1, String.format("Your ad %s has successfully been approved and can now be found among Ad listings", ad1.getTitleDescription().getTitle()));
    }

    private void emailUser(Ad ad, String message) {
        Notification notification = Notification.builder()
                .plainText(message)
                .receipient(ad.getCreatedBy())
                .subject("Ad Approval")
                .build();
        notificationService.prepareAndSend(notification);
    }

    public void reject(Ad ad) {
        Assert.notNull(ad.getRejectionReason(), "Please enter reason for rejection");
        Ad ad1 = this.findById(ad.getId()).orElseThrow(() -> new ApplicationException("Resource not found"));
        ad1.setAuthorized(Boolean.FALSE);
        ad1.setRejectionReason(ad.getRejectionReason());
        this.adRepository.save(ad1);
        emailUser(ad1, String.format("Your ad %s was not been approved due to %s. Please contact customer support with this code %s.", ad1.getTitleDescription().getTitle(),
                ad.getRejectionReason(), ad1.getCode()));
    }

    @Override
    public Page<Ad> findAll(Ad ad, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Ad> findById(Long id) {
        return adRepository.findById(id);
    }

    @Override
    public Page<Ad> findAll(SearchRequest request) {
//        System.out.println("Current User Logged In: " + UserService.getCurrentUser());
        return adRepository.findAll(new Ad().predicates(request).and(QAd.ad.createdBy.eq(UserService.getCurrentUser())),
                PageRequest.of(request.getPaging().getPage(), request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    public Page<Ad> pendingAds(SearchRequest request) {
        return adRepository.findAll(new Ad().predicates(request)
                .and(QAd.ad.authorized.isFalse()).and(QAd.ad.rejectionReason.isNull()), PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    public Page<Ad> featuredAds(SearchRequest request) {
        BooleanExpression expression = new Ad().predicates(request).and(QAd.ad.createdBy.eq(UserService.getCurrentUser()));
        if (adRepository.count(expression.and(QAd.ad.featured.isTrue())) >= 4) {
            expression.and(QAd.ad.featured.isTrue());
        } else {
            expression.and(QAd.ad.featured.isFalse());
        }
        return adRepository.findAll(expression, PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    public Page<Ad> archivedAds(SearchRequest request) {
        return adRepository.findAll(new Ad().predicates(request)
                .and(QAd.ad.archived.isTrue()), PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    public Page<Ad> sponsoredAds(SearchRequest request) {
        return adRepository.findAll(new Ad().predicates(request).and(QAd.ad.sponsored.isTrue()), PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    // PUBLIC
    public Page<Ad> findAllAds(SearchRequest request) {
        return adRepository.findAll(new Ad().predicates(request).and(QAd.ad.authorized.isTrue()),
                PageRequest.of(0, 24, Sort.by(Sort.Direction.DESC, "createdDate")));
    }

    public Page<Ad> findAllAdsAdvance(Ad ad) {
        return adRepository.findAll(ad.filterPredicates().and(QAd.ad.authorized.isTrue()), ad.getPaging().getPageRequest());
    }

    public List<DataGroup> groupByPickListCode(String catCode) {
        return adRepository.findBrandCount(catCode);
    }

    public Page<Ad> findAdListing(NTuple request) {
        return adRepository.findAll(queryBuilder(request), request.getPaging().getPageRequest());
    }

    private BooleanBuilder queryBuilder(NTuple request) {
        QAd qAd = QAd.ad;
        BooleanBuilder builder = new BooleanBuilder();
        if (!StringUtils.isEmpty(request.getCategory())) {
            builder.and(qAd.category.code.eq(request.getCategory()));
        }
        if (!StringUtils.isEmpty(request.getBrands())) {
            builder.and(qAd.brand.code.in(request.getBrands()));
        }
        if (!ObjectUtils.isEmpty(request.getItemCondition())) {
            if (request.getItemCondition().contains(",")) {
                List<Condition> collect = Stream.of(request.getItemCondition().split(",")).map(Condition::valueOf).collect(Collectors.toList());
                builder.and(qAd.itemCondition.in(collect));
            } else {
                builder.and(qAd.itemCondition.eq(Condition.valueOf(request.getItemCondition())));
            }
        }
        if (!StringUtils.isEmpty(request.getSubCategory())) {
            builder.and(qAd.subCategory.code.eq(request.getSubCategory()));
        }
        if (!StringUtils.isEmpty(request.getPrice())) {
            String amount = request.getPrice();
            if (amount.contains("-")) {
                String[] amounts = amount.split("-");
                if (!StringUtils.isEmpty(amounts[0]) && !StringUtils.isEmpty(amounts[1])) {
                    builder.and(qAd.price.amount.between(new BigDecimal(amounts[0]), new BigDecimal(amounts[1])));
                }
                if (!StringUtils.isEmpty(amounts[0]) && StringUtils.isEmpty(amounts[1])) {
                    builder.and(qAd.price.amount.between(new BigDecimal(amounts[0]), new BigDecimal("9999999999999999999999999999")));
                }
                if (StringUtils.isEmpty(amounts[0]) && !StringUtils.isEmpty(amounts[1])) {
                    builder.and(qAd.price.amount.between(BigDecimal.ZERO, new BigDecimal(amounts[1])));
                }
            } else {
                builder.and(qAd.price.amount.between(new BigDecimal(amount), new BigDecimal("9999999999999999999999999999")));
            }
        }
        builder.and(qAd.authorized.isTrue());
        return builder;
    }

    public Page<Ad> adHistory(SearchRequest request) {
        BooleanExpression predicates = new Ad().predicates(request).and(QAd.ad.authorized.isTrue()).or(QAd.ad.authorized.isFalse().and(QAd.ad.rejectionReason.isNotNull()));
        if (!StringUtils.isBlank(request.getSearchTerm())) {
            predicates.and(QAd.ad.createdBy.eq(request.getSearchTerm()));
        }
        System.out.println("Query " + predicates.toString());
        return adRepository.findAll(predicates, PageRequest.of(request.getPaging().getPage(), request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    public Page<Ad> favoriteAds(SearchRequest request, List<Long> ids) {
        return adRepository.findAll(new Ad().predicates(request).and(QAd.ad.id.in(ids)),
                PageRequest.of(request.getPaging().getPage(), request.getPaging().getLimit(), request.getPaging().getSort()));
    }

}
