package org.telegram.ui.Components.Premium;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class PremiumNotAvailableBottomSheet extends BottomSheet {

    public PremiumNotAvailableBottomSheet(BaseFragment fragment) {
        super(fragment.getParentActivity(), false);
        Context context = fragment.getParentActivity();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(context);
        title.setGravity(Gravity.START);
        title.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        title.setTypeface(AndroidUtilities.getTypeface("fonts/rregular.ttf"));

        linearLayout.addView(title, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, 0, 21, 16, 21, 0));

        TextView description = new TextView(context);
        description.setGravity(Gravity.START);
        description.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        description.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        linearLayout.addView(description, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, 0, 21, 15, 21, 16));

        TextView buttonTextView = new TextView(context);
        buttonTextView.setPadding(AndroidUtilities.dp(34), 0, AndroidUtilities.dp(34), 0);
        buttonTextView.setGravity(Gravity.CENTER);
        buttonTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        buttonTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        buttonTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rregular.ttf"));
        buttonTextView.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, 8));
        buttonTextView.setText(LocaleController.getString(R.string.InstallOfficialApp));
        buttonTextView.setOnClickListener(v -> {
            try {
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")));
            } catch (ActivityNotFoundException e) {
                FileLog.e(e);
            }
        });

        TextView buttonTextViewBot = new TextView(context);
        buttonTextViewBot.setPadding(AndroidUtilities.dp(34), 0, AndroidUtilities.dp(34), 0);
        buttonTextViewBot.setGravity(Gravity.CENTER);
        buttonTextViewBot.setTextColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        buttonTextViewBot.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        buttonTextViewBot.setTypeface(AndroidUtilities.getTypeface("fonts/rregular.ttf"));
        buttonTextViewBot.setBackground(Theme.AdaptiveRipple.filledRect(Theme.key_dialogBackground, 8));
        buttonTextViewBot.setText(LocaleController.getString(R.string.OpenPremiumBot));
        buttonTextViewBot.setOnClickListener(v -> {
            MessagesController.getInstance(UserConfig.selectedAccount).openByUserName(("PremiumBot"), fragment, 1);
        });

        FrameLayout buttonContainer = new FrameLayout(context);
        buttonContainer.addView(buttonTextView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, 48, Gravity.TOP, 16, 0, 16, 0));
        buttonContainer.addView(buttonTextViewBot, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, 48, Gravity.TOP, 16, 56, 16, 0));
        buttonContainer.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        linearLayout.addView(buttonContainer, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, 104, Gravity.BOTTOM));

        title.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.SubscribeToPremiumOfficialAppNeeded)));
        description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.SubscribeToPremiumOfficialAppNeededDescription)));
        ScrollView scrollView = new ScrollView(context);
        scrollView.addView(linearLayout);
        setCustomView(scrollView);
    }
}
