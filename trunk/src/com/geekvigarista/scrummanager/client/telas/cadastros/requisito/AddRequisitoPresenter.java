package com.geekvigarista.scrummanager.client.telas.cadastros.requisito;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootPopupContentEvent;

public class AddRequisitoPresenter extends Presenter<AddRequisitoPresenter.AddRequisitoView, AddRequisitoPresenter.AddRequisitoProxy>
{
	
	@ProxyCodeSplit
	@NameToken(NameTokens.addreq)
	public interface AddRequisitoProxy extends ProxyPlace<AddRequisitoPresenter>
	{
		
	}
	
	public interface AddRequisitoView extends PopupView
	{
		DialogBox getDialog();
	}
	
	private final DispatchAsync dispatcher;
	private Projeto projeto;
	
	@Inject
	public AddRequisitoPresenter(final EventBus eventBus, final AddRequisitoView view, final AddRequisitoProxy proxy, final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		String id = request.getParameter(Parameters.projid, null);
		if(id == null)
			return; //FIXME tratar essa pica aqui
			
		dispatcher.execute(new LoadProjetoAction(id), new AsyncCallback<LoadProjetoResult>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				// FIXME tratar
			}
			
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
				System.out.println(projeto.getNome());
				getView().getDialog().setText(projeto.getNome());
			}
		});
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
	}
	
	@Override
	protected void revealInParent()
	{
		RevealRootPopupContentEvent.fire(this, this);
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
	}
	
}
