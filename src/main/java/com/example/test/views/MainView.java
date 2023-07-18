package com.example.test.views;

import com.example.test.data.entity.Note;
import com.example.test.data.service.NoteService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("main")
//@RequiredArgsConstructor
public class MainView extends VerticalLayout {

    private final NoteService noteService;
    private Binder<Note> binder;
    private Note note;
    private HorizontalLayout horizontalLayout;
    private IntegerField textField;
    private Button button;

    @Autowired
    public MainView(NoteService noteService) {
        this.noteService = noteService;

        createView();

        note = new Note();
        binder = new Binder<>(Note.class);
        binder.bind(textField, Note::getValue, Note::setValue);

        incrementValue();

        saveNote();

        horizontalLayout.add(textField, button);
        add(horizontalLayout);
    }

    private void saveNote() {
        textField.addValueChangeListener(e -> {
            if (binder.writeBeanIfValid(note) && note != null) {
                System.out.println(note.toString());
                noteService.saveNote(note);
            } else {
                throw new NullPointerException("Note is null");
            }
        });
    }

    private void incrementValue() {
        button.addClickListener(e -> {
            var value = textField.getValue();
            value++;
            textField.setValue(value);
        });
    }

    private void createView() {
        horizontalLayout = new HorizontalLayout();
        button = new Button("+");
        textField = new IntegerField("Введите число");
        textField.setValue(0);
    }
}
