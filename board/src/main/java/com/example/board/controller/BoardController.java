package com.example.board.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.entity.Board;
import com.example.board.entity.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.LikeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	@Autowired
	BoardRepository boardRepository;

	@Autowired
	LikeRepository likeRepository;
	@GetMapping("/board/like")
	public String boardLike(
		
		@RequestParam long id,
		HttpSession session
	) {
		Board board = new Board();
		board.setId(id);

		User user = (User) session.getAttribute("user_infon");
		likeRepository.findByBoardAndUser(board, null);

		// if(like)

		return "redirect:/board";
	}


	@GetMapping("/board/delete")
	public String boardDelete(
		HttpSession session,
		@RequestParam int id
	) {
		Board board = new Board();
		board.setId((long)id);
		Optional<Board> boards = boardRepository.findById((long) id);
		if(boards.get().getUser().getId() == ((User)session.getAttribute("user")).getId()){
			boardRepository.delete(board);
		}

		return "redirect:/board";
	}

	@GetMapping("/board/update")
	public String boardUpdate(
		@RequestParam int id,
		Model model
		) {
			Optional<Board> boards = boardRepository.findById((long) id);
		model.addAttribute("board", boards.get());

		return "board/update";
	}

	@PostMapping("/board/update")
	public String boardUpdatePost(
		@RequestParam int id,
		Model model,
		Board board,
		HttpSession session

	) {
		Optional<Board> boards = boardRepository.findById((long) id);
		java.util.Date date = new Date();
		board.setDate(date);
		System.out.println(boards.get().getUser());
		System.out.println(session.getAttribute("user"));
		if(session.getAttribute("user") == null){
			System.out.println("user info null");
		}
		else if(boards.get().getUser().getId() != ((User)session.getAttribute("user")).getId()){
			System.out.println("user missmatch");
		}
		else{
			board.setUser((User)session.getAttribute("user"));
			boardRepository.save(board);
		}
		return "redirect:/board";
	}

	@GetMapping("/board/view")
	public String boardView(
			@RequestParam (defaultValue = "1")int page,
			@RequestParam(defaultValue = "1") int id,
			Model model) {
				System.out.println("page : "  +page);
		Optional<Board> boards = boardRepository.findById((long) id);
		model.addAttribute("board", boards.get());
		model.addAttribute("page", page);
		return "board/view";
	}

	@GetMapping("/board")
	public String board() {

		return "redirect:/board/list";
	}

	
	

	@GetMapping("/board/list")
	public String boardList(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String search,
			Model model) {
		int startPage = (page - 1) / 10 * 10 + 1;
		int endPage = startPage + 9;

		if(search == ""){
			Pageable pageable = PageRequest.of(page - 1, 10);
			Page<Board> p = boardRepository.findAll(pageable);
			// List<Board> list = boardRepository.findAll();
			model.addAttribute("list", p.getContent());
		}else{
			// List<Board> list = boardRepository.findByTitleContaining(search);
			// model.addAttribute("list", list);
			Pageable pageable = PageRequest.of(page-1,10);
			Page<Board> list = boardRepository.findByTitleContaining(search , pageable);
			model.addAttribute("list", list.getContent());
		}
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("page", page);

		return "board/list";
	}

	@GetMapping("/board/write")
	public String boardWrite(
		HttpSession session
	) {
		User user = (User) session.getAttribute("user_info");
		// if user == null
		if(session.getAttribute("user") == null){
			System.out.println("로그인부터 하세요");
			return "redirect:/signin?redirect:/board/write";
		}
		return "board/write";
	}

	@PostMapping("/board/write")
	public String boardWritePost(
		Board board,
		HttpSession session
		) {
		
		if(session.getAttribute("user") == null){
			System.out.println("로그인부터 하세요");
		}
		else{
			java.util.Date date = new Date();
			board.setDate(date);
			board.setUser((User)session.getAttribute("user"));
		}
		boardRepository.save(board);
		return "redirect:/board";
	}
}