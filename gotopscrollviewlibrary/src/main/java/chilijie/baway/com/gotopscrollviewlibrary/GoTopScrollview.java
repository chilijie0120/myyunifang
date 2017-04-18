package chilijie.baway.com.gotopscrollviewlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * Created by MacBook- on 2017/4/11.
 */
public class GoTopScrollview extends ScrollView implements View.OnClickListener {
    private int height=300;
    private ImageView iv;
    public GoTopScrollview(Context context) {
        super(context);
    }

    public GoTopScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoTopScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
   public void setHeight(int height){
       this.height=height;
   }
    public void setImgeViewOnClickListener(ImageView iv) {
        this.iv = iv;
        this.iv.setOnClickListener(this);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (height!=0){
            if (t>height){
                iv.setVisibility(VISIBLE);
            }else{
                iv.setVisibility(GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
       this.scrollTo(0,0);
    }
}
