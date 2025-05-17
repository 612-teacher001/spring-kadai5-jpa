package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	/**
	 * Step1：商品一覧画面を表示する
	 * @return 遷移先ThymeLeafテンプレート名
	 */
	@GetMapping("/items")
	public String index(Model model) {
		// データベースからすべての商品の商品リストを取得
		List<Item> list = itemRepository.findAll();
		// 商品リストをデータ置き場に登録
		model.addAttribute("items", list);
		// 画面遷移
		return "example/items";
	}
}
