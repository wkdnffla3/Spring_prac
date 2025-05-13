package com.example.board.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.entity.Board;
import com.example.board.entity.FileAtch;
import com.example.board.entity.Like;
import com.example.board.entity.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.FileAtchRepository;
import com.example.board.repository.LikeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	@Autowired
	BoardRepository boardRepository;

	@Autowired LikeRepository likeRepository;
	@Autowired FileAtchRepository fileAtchRepository;
	
	@GetMapping("/board/like")
	public String boardLike(
			@RequestParam Long id, HttpSession session) {
		Board board = new Board();
		board.setId(id);

		User user = (User) session.getAttribute("user_info");

		Like like = likeRepository.findByBoardAndUser(board, user);
		if(like != null) { // 좋아요 한적 있음
			likeRepository.delete(like);
		} else { // 좋아요 한적 없음
			like = new Like();
			like.setBoard(board);
			like.setUser(user);
			likeRepository.save(like);
		}

		return "redirect:/board";
	}
	
	@GetMapping("/board/delete")
	public String boardDelete(@RequestParam Long id) {
		boardRepository.deleteById(id);
		return "redirect:/board";
	}

	@GetMapping("/board/update")
	public String boardUpdate(
			@RequestParam Long id, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("user_info");
		Optional<Board> opt = boardRepository.findById(id);

		Board board = opt.orElseGet(() -> new Board());
		User savedUser = board.getUser();

		// 0x100     0x200    // WrapperClass Long,  String
		if(user == null || user.getEmail().equals(savedUser.getEmail())) {
			return "error_signin";
		}

		model.addAttribute("board", board);
		return "board/update";
	}
		
	@PostMapping("/board/update")
	public String boardUpdatePost(@ModelAttribute Board board) {
	  Optional<Board> opt = boardRepository.findById(board.getId());
		Board savedBoard = opt.orElseGet(() -> new Board());
		board.setUser(savedBoard.getUser());
		boardRepository.save(board);
		return "redirect:/board";
	}

	@GetMapping("/board/view")
	public String boardView(@RequestParam Long id, Model model) {
		Optional<Board> opt = boardRepository.findById(id);
		Board board = opt.orElseGet(() -> new Board());
		model.addAttribute("board", board);
		return "board/view";
	}

	@GetMapping("/board")
	public String board() {

		return "redirect:/board/list";
	}

	@GetMapping("/board/list")
	public String boardList(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String q,
			Model model) {
		
		Sort sort = Sort.by(Direction.DESC, "id");		
		Pageable pageable = PageRequest.of(page - 1, 10, sort);
		
		Page<Board> p = 
			boardRepository.findByTitleContaining(q, pageable);
		List<Board> list = p.getContent();
		
		System.out.println(list);

		Long totalElements = p.getTotalElements();
		int totalPages = p.getTotalPages();
		//   12    endPage 10
		int startPage = (page - 1) / 10 * 10 + 1;
		int endPage = Math.min(startPage + 9, totalPages);

		model.addAttribute("list", list);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("totalPages", totalPages);

		return "board/list";
	}

	@GetMapping("/board/write")
	public String boardWrite(HttpSession session) {
		User user = (User) session.getAttribute("user_info");
		if(user == null) {
			return "redirect:/signin?redirect=/board/write";
		}
		return "board/write";
	}
	@Transactional
	@PostMapping("/board/write")
	public String boardWritePost(
			@ModelAttribute Board board, HttpSession session,
			@RequestParam MultipartFile file) {
		User user = (User) session.getAttribute("user_info");
		board.setUser(user);
		Board savedBoard = boardRepository.save(board);

		FileAtch fileAtch = new FileAtch();
		fileAtch.setBoard(savedBoard);
		
		String oName = file.getOriginalFilename();
		fileAtch.setOName(oName);
		
		String ext = oName.substring(oName.lastIndexOf("."));
		String cName = UUID.randomUUID() + ext;
		fileAtch.setCName(cName);

		fileAtchRepository.save(fileAtch);

		// 실제 파일을 저장 transferTo()

		return "redirect:/board";
	}
}