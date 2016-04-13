package com.example.webshop;

import com.example.webshop.model.Product;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class ProductOverview extends CssLayout implements View{
	
	public final static String VIEW_NAME = "productoverview";
	public final ProductTable table;

	
	
	public ProductOverview(){
		setSizeFull();
        addStyleName("crud-view");
        HorizontalLayout topLayout = createTopBar();

        table = new ProductTable();
        table.addItemClickListener(new ItemClickListener() {
			
			
			@Override
			public void itemClick(ItemClickEvent event) {
                Notification.show("Selected: " + event.getItemId());
				
			}
		});


        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.addComponent(topLayout);
        barAndGridLayout.addComponent(table);
        barAndGridLayout.setMargin(true);
        barAndGridLayout.setSpacing(true);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.setExpandRatio(table, 1);
        barAndGridLayout.setStyleName("crud-main-layout");

        addComponent(barAndGridLayout);

        //viewLogic.init();
		
	}

	
	public HorizontalLayout createTopBar() {
        TextField filter = new TextField();
        filter.setStyleName("filter-textfield");
        filter.setInputPrompt("Filter");
        filter.setImmediate(true);
        filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                table.setFilter(event.getText());
            }
        });

        

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth("100%");
        topLayout.addComponent(filter);
        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");
        return topLayout;
    }
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
		table.setJPAContainer();
		
	}

}
