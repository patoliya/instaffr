package com.pingbits.instaffr_app.views;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.pingbits.instaffr_app.R;

public class NavDrawer implements Drawer.OnDrawerItemClickListener {

    private final MainActivity activity;

    private Drawer drawer;

    private AccountHeader header;

    public NavDrawer(MainActivity activity, Toolbar toolbar) {
        this.activity = activity;
        initHeader();
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {

        int user_icon_color = activity.getResources().getColor(R.color.user_icon_color);

        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(header)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(activity.getString(R.string.add_connections))
                                .withIcon(R.drawable.ic_person_add_black_24dp)
                                .withIconTintingEnabled(true)
                                .withCheckable(false)
                                .withIconColor(Color.GRAY),
                        new SectionDrawerItem()
                                .withName(activity.getString(R.string.recent_connections)),
                        new PrimaryDrawerItem()
                                .withName("Jaydeep")
                                .withIcon(R.drawable.ic_person_black_24dp)
                                .withIconTintingEnabled(true)
                                .withCheckable(false)
                                .withIconColor(user_icon_color),
                        new PrimaryDrawerItem()
                                .withName("Parth")
                                .withIcon(R.drawable.ic_person_black_24dp)
                                .withIconTintingEnabled(true)
                                .withCheckable(false)
                                .withIconColor(user_icon_color),
                        new PrimaryDrawerItem()
                                .withName("Nidhi")
                                .withIcon(R.drawable.ic_person_black_24dp)
                                .withIconTintingEnabled(true)
                                .withCheckable(false)
                                .withIconColor(user_icon_color),
                        new SecondaryDrawerItem()
                                .withName("More")
                                .withIcon(R.drawable.ic_people_black_24dp)
                                .withIconTintingEnabled(true)
                                .withCheckable(false)
                                .withIconColor(Color.GRAY),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withName("Settings")
                                .withIcon(R.drawable.ic_settings_black_24dp)
                                .withIconTintingEnabled(true)
                                .withCheckable(false)
                                .withIconColor(Color.GRAY),
                        new PrimaryDrawerItem()
                                .withName("Help")
                                .withIcon(R.drawable.ic_help_black_24dp)
                                .withIconTintingEnabled(true)
                                .withCheckable(false)
                                .withIconColor(Color.GRAY),
                        new PrimaryDrawerItem()
                                .withName("About")
                                .withIcon(R.drawable.ic_info_black_24dp)
                                .withIconTintingEnabled(true)
                                .withCheckable(false)
                                .withIconColor(Color.GRAY)
                ).withOnDrawerItemClickListener(this)
                .withSelectedItem(-1)
                .build();

    }

    private void initHeader() {
        Drawable backdrop = activity.getResources().getDrawable(R.drawable.stock_backdrop);
        assert backdrop != null;
        backdrop.setColorFilter(activity.getResources().getColor(R.color.primary_light), PorterDuff.Mode.MULTIPLY);
        header = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(backdrop)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Jaydeep")
                                .withEmail("jaydp17@gmail.com")
                                .withIcon(activity.getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
    }

    @Override
    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
        return false;
    }
}
