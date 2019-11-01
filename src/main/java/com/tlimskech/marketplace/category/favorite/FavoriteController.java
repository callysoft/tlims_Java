package com.tlimskech.marketplace.category.favorite;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.auth.user.UserService;
import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;
    private final AdService adService;

    public FavoriteController(FavoriteRepository favoriteRepository, AdService adService) {
        this.favoriteRepository = favoriteRepository;
        this.adService = adService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> add(@RequestBody Favorite favorite) {
        Optional<Favorite> favorite1 = favoriteRepository.findByPostIdAndCreatedBy(favorite.getPostId(), UserService.getCurrentUser());
        if (!favorite1.isPresent()) {
            favoriteRepository.save(favorite);
            return ResponseEntity.ok("Post successfully added to favorites");
        }
        favoriteRepository.delete(favorite1.get());
        return ResponseEntity.ok("Post successfully removed from favorites");
    }

    @GetMapping("/added/{postId}")
    public ResponseEntity<?> added(@PathVariable Long postId) {
        return ResponseEntity.ok(favoriteRepository.findByPostIdAndCreatedBy(postId, UserService.getCurrentUser()).isPresent());
    }

    @PostMapping("/list")
    public ResponseEntity<?> list(@RequestBody SearchRequest request) {
        List<Long> longList = favoriteRepository.findByCreatedBy(UserService.getCurrentUser()).stream().map(Favorite::getPostId).collect(Collectors.toList());
        return ResponseEntity.ok(adService.favoriteAds(request, longList));
    }
}
