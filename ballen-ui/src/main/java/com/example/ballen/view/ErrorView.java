package com.example.ballen.view;

import javax.servlet.http.HttpServletResponse;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.ParentLayout;

/**
 * View shown when trying to navigate to a view that does not exist using
 */
@ParentLayout(DefaultLayout.class)
public class ErrorView extends VerticalLayout implements HasErrorParameter<NotFoundException> {

    private Span explanation;

    public ErrorView() {
        add(new H1(getTranslation("error.title")));

        explanation = new Span();
        add(explanation);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        explanation.setText(getTranslation("error.text", event.getLocation().getPath()));
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
