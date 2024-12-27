package com.springframework.CareerConnect.controllers;

import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.Tag;
import com.springframework.CareerConnect.model.TagDTO;
import com.springframework.CareerConnect.services.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/tags")
@Slf4j
public class TagController {

    private final MapStructMapper mapStructMapper;
    private final TagService tagService;

    public TagController(MapStructMapper mapStructMapper, TagService tagService) {
        this.mapStructMapper = mapStructMapper;
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        log.info("Get all tags");
        List<Tag> tags = tagService.getAllTags();

        List<TagDTO> tagDTOS = tags.stream()
                .map(mapStructMapper::mapToTagDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tagDTOS);
    }


    @GetMapping("/{tagId}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Long tagId) {
        log.info("Get tag by id {}", tagId);
        Tag tag = tagService.getTagById(tagId);
        TagDTO tagDTO = mapStructMapper.mapToTagDTO(tag);

        return ResponseEntity.ok(tagDTO);
    }
}
