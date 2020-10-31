package io.github.kamaravichow.shelftabs;

import android.view.View;


public interface ITabView {

    ITabView setBadge(TabView.TabBadge badge);

    ITabView setIcon(TabView.TabIcon icon);

    ITabView setTitle(TabView.TabTitle title);

    ITabView setBackground(int resid);

    TabView.TabBadge getBadge();

    TabView.TabIcon getIcon();

    TabView.TabTitle getTitle();

    View getTabView();

}
