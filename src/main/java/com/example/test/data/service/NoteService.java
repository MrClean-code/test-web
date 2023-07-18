package com.example.test.data.service;

import com.example.test.data.entity.Note;
import com.example.test.data.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService  {

    private final NoteRepository noteRepository;

    public Note saveNote(Note note){
        noteRepository.save(note);
        return note;
    }
}

