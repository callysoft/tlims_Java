package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.auth.user.UserService;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import com.tlimskech.marketplace.exception.ApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class AdService implements BaseService<Ad, Long> {

    private final AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Ad create(Ad ad) {
        ad.setNegotiable(!isEmpty(ad.getNegotiable()) ? ad.getNegotiable() : FALSE);
        ad.setAuthorized(!isEmpty(ad.getAuthorized()) ? ad.getAuthorized() : FALSE);
        ad.setFeatured(!isEmpty(ad.getFeatured()) ? ad.getFeatured() : FALSE);
        ad.setArchived(!isEmpty(ad.getArchived()) ? ad.getArchived() : FALSE);
        return adRepository.save(ad);
    }

    @Override
    public Ad update(Ad ad) {
        Ad found = adRepository.findById(ad.getId()).orElseThrow(() -> new ApplicationException("Resource not found"));
        found.copyForUpdate(ad);
        return adRepository.save(found);
    }

    @Override
    public void delete(Ad ad) {

    }

    public void activate(Long id) {
        Ad ad1 = this.findById(id).orElseThrow(() -> new ApplicationException("Resource not found"));
        ad1.setAuthorized(Boolean.TRUE);
        this.adRepository.save(ad1);
    }

    public void deactivate(Long id) {
        Ad ad1 = this.findById(id).orElseThrow(() -> new ApplicationException("Resource not found"));
        ad1.setAuthorized(Boolean.FALSE);
        this.adRepository.save(ad1);
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
                .and(QAd.ad.authorized.isFalse()), PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    public Page<Ad> featuredAds(SearchRequest request) {
        return adRepository.findAll(new Ad().predicates(request).and(QAd.ad.createdBy.eq(UserService.getCurrentUser()))
                .and(QAd.ad.featured.isFalse()), PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    public Page<Ad> archivedAds(SearchRequest request) {
        return adRepository.findAll(new Ad().predicates(request).and(QAd.ad.createdBy.eq(UserService.getCurrentUser()))
                .and(QAd.ad.archived.isFalse()), PageRequest.of(request.getPaging().getPage(),
                request.getPaging().getLimit(), request.getPaging().getSort()));
    }

    // PUBLIC
    public Page<Ad> findAllAds(SearchRequest request) {
        return adRepository.findAll(new Ad().predicates(request).and(QAd.ad.authorized.isTrue()),
                PageRequest.of(0, 24, Sort.by(Sort.Direction.DESC, "createdDate")));
    }

}
