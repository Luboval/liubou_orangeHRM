package eu.senla.api.apielements.dashboard.buzzlatestposts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.senla.api.apielements.users.UserDataEmployee;

import java.util.List;
import java.util.Optional;

public record BuzzLatestPost(
        int id,
        BuzzPost post,
        String type,
        boolean liked,
        String text,
        UserDataEmployee employee,
        BuzzStats stats,
        String createdDate,
        String createdTime,
        String originalPost,
        BuzzPermission permission,
        List<Integer> photoIds
) {
    // Скрываем стандартный аксессор от Jackson
    @JsonIgnore
    public List<Integer> photoIds() {
        return photoIds;
    }

    // Создаем свой метод для Optional
    public Optional<List<Integer>> getPhotoIds() {
        return Optional.ofNullable(photoIds);
    }
}
