package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	/**
	 * Step3：カテゴリ別商品検索一覧画面を表示する
	 * @param maxPrice 検索商品の価格上限値
	 * @param categoryId 検索対象商品のカテゴリコード
	 * @param model 共用のデータ置き場
	 * @return 遷移先ThymeLeafテンプレート名
	 */
	@GetMapping("/items")
	public String index(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sort", defaultValue = "") String sort,
			@RequestParam(name = "maxPrice", defaultValue = "") Integer maxPrice,
			@RequestParam(name = "categoryId", defaultValue = "0") int categoryId,
			Model model) {
		// 【step3での変更箇所】価格上限値によるデータベースから上限値以下の価格の商品の商品リストを取得
		List<Item> list = null;
		if (!keyword.isEmpty() || maxPrice != null) {
			if (!keyword.isEmpty()) {
				if (maxPrice == null) {
					// キーワード検索
					list = itemRepository.findByNameContains(keyword);
				} else {
					// キーワード検索と価格上限値検索
					list = itemRepository.findByNameContainsAndPriceLessThanEqual(keyword, maxPrice);
				}
			} else {
				if (maxPrice == null) {
					// 全件検索
					list = itemRepository.findAll();
				} else {
					// 価格上限値検索
					list = itemRepository.findByPriceLessThanEqual(maxPrice);
				}
			}
			model.addAttribute("keyword", keyword);
			model.addAttribute("maxPrice", maxPrice);
		} else {
			// 【step4での変更箇所】データベースから「安い順」に並べ替えた商品リストを取得
			if (sort.equals("priceAsc")) {
				list = itemRepository.findAllByOrderByPrice();
			} else {
				// 【step2での変更箇所】カテゴリコードによるデータベースからすべての商品の商品リストを取得
				if (categoryId == 0) {
					// 全商品検索の場合
					list = itemRepository.findAll();
				} else {
					// カテゴリ検索（絞り込み検索）の場合
					list = itemRepository.findByCategoryId(categoryId);
				}
			}
		}
		
		// 商品リストをデータ置き場に登録
		model.addAttribute("items", list);
		// 画面遷移
		return "example/items";
	}
}
