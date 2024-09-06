package com.sparta.myselectshop.service;

import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public void addFolders(List<String> folderNames, User user) {

        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user,folderNames);

        List<Folder> folderList = new ArrayList<>();

        for(String folderName : folderNames){
            //DB에 폴더 이름이 없을 때
            if(!isExistFolderName(folderName,existFolderList)){
                Folder folder = new Folder(folderName,user);
                folderList.add(folder);
            }else{
                throw new IllegalArgumentException("폴더명이 중복 되었습니다.");
            }
        }

        folderRepository.saveAll(folderList);


    }

    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        for (Folder existFolder : existFolderList) {
            if(folderName.equals(existFolder.getName())){
                return true;
            }
        }

        return false;
    }
}