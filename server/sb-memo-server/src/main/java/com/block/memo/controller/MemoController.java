package com.block.memo.controller;

import com.block.memo.entity.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class MemoController {
    ArrayList<Memo> memoList = new ArrayList<>();

    @PostMapping("/memos")
    public Map<String, Object> createMemo(@RequestBody Memo memo){
        memoList.add( memo );
        return Map.of( "message", "잘 저장되었습니다" ) ;
    }

    @GetMapping("/memos/{id}")
    public Map<String, Object> getMemo(@PathVariable long id){
        for (  Memo memo : memoList){
            if(memo.id == id){
                return Map.of("memo", memo) ;
            }
        }
        return Map.of("memo" , "아이디가 잘못됬다.") ;
    }

    @PutMapping("/memos/{id}")
    public Map<String, Object> updateMemo(@PathVariable long id, @RequestBody Memo memo){
        for( Memo savedMemo : memoList){
            if( savedMemo.id == id ){
                savedMemo.title = memo.title;
                savedMemo.content = memo.content;
                return Map.of("message", "수정 완료") ;
            }
        }
        return Map.of("message", "해당 메모 없음") ;
    }

    @DeleteMapping("/memos/{id}")
    public Map<String, Object> deleteMemo(@PathVariable long id){
        for(  Memo memo : memoList){
            if( memo.id == id ){
                memoList.remove(memo);
                return Map.of("message", "삭제 완료") ;
            }
        }
        return Map.of("message", "해당 메모 없음") ;
    }

}
