package com.geekvigarista.scrummanager.client.telas.cadastros.requisito;

import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddRequisitoView extends ViewImpl implements AddRequisitoPresenter.AddRequisitoView
{
	
	public AddRequisitoView()
	{
		uiBinder.createAndBindUi(this);
	}
	
	private static AddRequisitoViewUiBinder uiBinder = GWT.create(AddRequisitoViewUiBinder.class);
	
	interface AddRequisitoViewUiBinder extends UiBinder<Widget, AddRequisitoView>
	{
	}
	
	private class RequisitoCell extends AbstractCell<Requisito>
	{
		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, Requisito value, SafeHtmlBuilder sb)
		{
			if(value != null)
			{
				sb.appendEscaped(value.getTitulo()).appendEscaped(" (").append(value.getTempoEstimado()).appendEscaped(" hrs)");
			}
		}
	}
	
	ProvidesKey<Requisito> KEY_PROVIDER = new ProvidesKey<Requisito>()
	{
		@Override
		public Object getKey(Requisito item)
		{
			return (item == null) ? null : item.getId();
		}
	};
	
	final SingleSelectionModel<Requisito> selectionModel = new SingleSelectionModel<Requisito>(KEY_PROVIDER);
	
	@UiField
	HTMLPanel conteudo;
	
	@UiField
	TextBox titulo;
	
	@UiField
	IntegerBox tempoEstimado;
	
	@UiField
	ListBox prioridade;
	
	@UiField
	Button btSalvar;
	
	@UiField
	Hyperlink btCancelar;
	
	@UiField
	CellList<Requisito> requisitos;
	
	@UiFactory
	CellList<Requisito> cellListFactory()
	{
		
		requisitos = new CellList<Requisito>(new RequisitoCell(), KEY_PROVIDER);
		
		requisitos.setSelectionModel(selectionModel);
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler()
		{
			@Override
			public void onSelectionChange(SelectionChangeEvent event)
			{
				System.out.println(selectionModel.getSelectedObject());
				// TODO tratar aqui ou mandar isso pro presenter.
			}
		});
		
		return requisitos;
	}
	
	@Override
	public Widget asWidget()
	{
		return conteudo;
	}
	
	@Override
	public SingleSelectionModel<Requisito> selectionModel()
	{
		return selectionModel;
	}
	
	@Override
	public HasClickHandlers getBtCancelar()
	{
		return btCancelar;
	}
	
	@Override
	public HasClickHandlers getBtSalvar()
	{
		return btSalvar;
	}

	@Override
	public HasValue<Integer> tempoEstimado()
	{
		return tempoEstimado;
	}

	@Override
	public HasValue<String> titulo()
	{
		return titulo;
	}

	@Override
	public ListBox prioridade()
	{
		return prioridade;
	}
}
