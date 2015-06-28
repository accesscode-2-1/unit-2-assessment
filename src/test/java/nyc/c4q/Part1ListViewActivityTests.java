package nyc.c4q;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Part1ListViewActivityTests {

    private ListViewActivity listViewActivity;

    @Before
    public void setUp() {
        listViewActivity = Robolectric.buildActivity(ListViewActivity.class).setup().get();
    }

    @Test //DONE
    public void test01ListViewActvityCheckFirstLevelHas2Views() {
        LinearLayout activity_listview = (LinearLayout) Helpers.findViewByIdString(listViewActivity, "activity_listview");
        assertThat(activity_listview.getChildCount(), equalTo(2));
        Assertions.assertThat(activity_listview).hasOrientation(LinearLayout.VERTICAL);
    }

    @Test //DONE
    public void test02ListViewActivityCheckFirstLevelLayoutViewHeader() {
        LinearLayout header = (LinearLayout) Helpers.findViewByIdString(listViewActivity, "header");
        assertThat(header, notNullValue());

        Assertions.assertThat(header.getLayoutParams()).hasWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        Assertions.assertThat(header.getLayoutParams()).hasHeight(0);
        assertThat(((LinearLayout.LayoutParams) header.getLayoutParams()).weight, equalTo(1.0f));
        Assertions.assertThat(header).hasOrientation(LinearLayout.HORIZONTAL);
    }
    @Test //DONE
    public void test03ListViewActivityCheckFirstLevelLayoutViewList() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        assertThat(list, notNullValue());

        Assertions.assertThat(list.getLayoutParams()).hasWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        Assertions.assertThat(list.getLayoutParams()).hasHeight(0);
        assertThat(((LinearLayout.LayoutParams) list.getLayoutParams()).weight, equalTo(9.0f));
    }

    @Test //DONE
    public void test04ListViewActivityCheckSecondLevelLayoutViewTextLog() {
        TextView textLog = (TextView) Helpers.findViewByIdString(listViewActivity, "textLog");

        assertThat(textLog, notNullValue());

        Assertions.assertThat(textLog.getLayoutParams()).hasWidth(0);
        Assertions.assertThat(textLog.getLayoutParams()).hasHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        assertThat(((LinearLayout.LayoutParams) textLog.getLayoutParams()).weight, equalTo(3.0f));
        Assertions.assertThat(textLog).containsText("You have not clicked anything.");
    }

    @Test //DONE
    public void test05ListViewActivityCheckSecondLevelLayoutViewAdapterCount() {
        EditText adapterCount = (EditText) Helpers.findViewByIdString(listViewActivity, "adapterCount");

        assertThat(adapterCount, notNullValue());

        Assertions.assertThat(adapterCount.getLayoutParams()).hasWidth(0);
        Assertions.assertThat(adapterCount.getLayoutParams()).hasHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        assertThat(((LinearLayout.LayoutParams) adapterCount.getLayoutParams()).weight, equalTo(1.0f));
        assertThat(adapterCount.getInputType(), equalTo(InputType.TYPE_CLASS_PHONE));
    }

    @Test //DONE
    public void test06ListViewActivityCheckAdapterCount() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));
    }

    @Test
    public void test07ListViewActivityCheckRowBackgroundColors() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        for (int i = 0; i < ListViewActivity.COLORS.length; i++) {
            View v = adapter.getView(i, null, list);
            assertThat(v, notNullValue());
            assertThat(v.getBackground(), instanceOf(ColorDrawable.class));
            ColorDrawable background = (ColorDrawable) v.getBackground();
            assertThat(background.getColor(), equalTo(Color.parseColor(ListViewActivity.COLORS[i])));
        }
    }

    @Test
    public void test08ListViewActivityViewClickChangesTextLog() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        TextView textLog = (TextView) Helpers.findViewByIdString(listViewActivity, "textLog");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        for (int i = 0; i < ListViewActivity.COLORS.length; i++) {
            View itemView = list.getChildAt(i);
            list.performItemClick(itemView, i, adapter.getItemId(i));
            Assertions.assertThat(textLog).containsText(String.format("You clicked on Item(position=%s, color=%s)", i, ListViewActivity.COLORS[i]));
        }
    }

    @Test
    public void test09ListViewActivityAdapterCountAdjustableInput() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        EditText adapterCount = (EditText) Helpers.findViewByIdString(listViewActivity, "adapterCount");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        for (int i = 0; i < 10 * ListViewActivity.COLORS.length; i++) {
            adapterCount.setText(Integer.toString(i));
            assertThat(adapter.getCount(), equalTo(i));
        }
    }

    @Test
    public void test10ListViewActivityAdapterCountInvalidInput() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        EditText adapterCount = (EditText) Helpers.findViewByIdString(listViewActivity, "adapterCount");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        adapterCount.setText("wonk");
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        adapterCount.setText("20");
        adapterCount.setText("fifteen");
        assertThat(adapter.getCount(), equalTo(20));

        adapterCount.setText("30");
        adapterCount.setText("zxcv");
        assertThat(adapter.getCount(), equalTo(30));
    }
}
