package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.ILikeService;
import com.example.springboot.entity.Like;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author snail
 * @since 2023-12-09
 */
@RestController
@RequestMapping("//like")
        public class LikeController {
    
    @Resource
    private ILikeService likeService;

// 新增或者更新
    @PostMapping
    public boolean save(@RequestBody Like like) {
        return likeService.saveOrUpdate(like);
        }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return likeService.removeById(id);
        }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return likeService.removeByIds(ids);
    }

    @GetMapping
    public List<Like> findAll() {
        return likeService.list();
    }

    @GetMapping("/{id}")
    public Like findOne(@PathVariable Integer id) {
        return likeService.getById(id);
    }

    @GetMapping("/page")
    public Page<Like> findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return likeService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    }

