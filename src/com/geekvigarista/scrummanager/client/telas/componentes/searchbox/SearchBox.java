package com.geekvigarista.scrummanager.client.telas.componentes.searchbox;

import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.Resources.SearchCss;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events.SearchEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events.SearchEventHandler;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Componente campo de busca estilizado com stakeholder :D
 * 
 * @author caarlos0
 * 
 */
public class SearchBox extends HorizontalPanel implements HasSearchHandlers, HasValue<String>
{
	/**
	 * Css.
	 */
	private static SearchCss css = Resources.CSS;
	
	static
	{
		StyleInjector.inject(css.getText());
	}
	
	private TextBox text;
	private PushButton button;
	
	/**
	 * Texto que fica "atras" do campo, "Digite aqui para pesquisar..." por exemplo.
	 */
	private String placeholder;
	
	/**
	 * Constructor.
	 */
	public SearchBox()
	{
		text = new TextBox();
		text.setStyleName(css.box());
		Image img = new Image(Resources.INSTANCE.lupe());
		button = new PushButton(img);
		button.addStyleName(css.button());
		add(text);
		add(button);
		bind();
	}
	
	public String getStakeholder()
	{
		return placeholder;
	}
	
	public void setPlaceholder(String placeholder)
	{
		this.placeholder = placeholder;
		text.setValue(placeholder);
		setBlurStyle();
		bindPlaceholderEvents();
	}
	
	private void bindPlaceholderEvents()
	{
		if(placeholder == null)
		{
			return;
		}
		
		text.addFocusHandler(new FocusHandler()
		{
			@Override
			public void onFocus(FocusEvent event)
			{
				if(text.getValue().trim().equals(placeholder))
				{
					text.setValue("");
				}
				setFocusStyle();
			}
		});
		
		text.addBlurHandler(new BlurHandler()
		{
			@Override
			public void onBlur(BlurEvent event)
			{
				if(text.getValue().trim().equals("") || text.getValue().trim().equals(getStakeholder()))
				{
					text.setValue(placeholder);
					setBlurStyle();
				}
			}
		});
	}
	
	private void setFocusStyle()
	{
		text.removeStyleName(css.blur());
		text.addStyleName(css.focus());
	}
	
	private void setBlurStyle()
	{
		text.removeStyleName(css.focus());
		text.addStyleName(css.blur());
	}
	
	private void bind()
	{
		text.addKeyUpHandler(new KeyUpHandler()
		{
			@Override
			public void onKeyUp(KeyUpEvent event)
			{
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					if(text.getValue().equals(placeholder))
						text.setValue("");
					fireEvent(new SearchEvent());
				}
			}
		});
		button.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if(text.getValue().equals(placeholder))
					text.setValue("");
				fireEvent(new SearchEvent());
			}
		});
	}
	
	@Override
	public HandlerRegistration addSearchHandler(final SearchEventHandler handler)
	{
		return addHandler(handler, SearchEvent.getType());
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler)
	{
		return text.addValueChangeHandler(handler);
	}
	
	@Override
	public String getValue()
	{
		return text.getValue();
	}
	
	@Override
	public void setValue(String value)
	{
		text.setValue(value);
		if(!value.equals(placeholder))
		{
			setFocusStyle();
		}
	}
	
	@Override
	public void setValue(String value, boolean fireEvents)
	{
		text.setValue(value, fireEvents);
	}
}
