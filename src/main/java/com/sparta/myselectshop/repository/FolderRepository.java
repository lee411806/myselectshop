package com.sparta.myselectshop.repository;

import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUserAndNameIn(User user, List<String> forderNames);

    List<Folder> findAllByUser(User user);
    // 쿼리메서드 쿼리 짜보기
    // select * from folder where user_id = ? and name in (폴더이름1, 폴더이름2, 폴더이름3 여기에 폴더이름 들어옴)
}
