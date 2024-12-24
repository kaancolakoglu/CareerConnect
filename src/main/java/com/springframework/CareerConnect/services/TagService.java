package com.springframework.CareerConnect.services;

import com.springframework.CareerConnect.domain.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {

    public List<Tag> getAllTags();

    public Tag getTagById(Long tagId);


}
