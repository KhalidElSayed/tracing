package com.example.myactivity;

import java.util.ArrayList;

import CustomObject.DrawView;
import CustomObject.MovingObject;
import CustomObject.TracedView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements AnimationListener {
	public static DragController dragC;
	// TextView _view;
	ViewGroup _root;
	MovingObject bee;

	ArrayList<TracedView> flowers = new ArrayList<TracedView>();
	DrawView drawView;
	int point_form = 0;
	int point_to = 0;
	int tx = 0;
	int ty = 0;
	ArrayList<Animation> animationList;
	int currentAnimation = 0;
	private MovingObject bee2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		animationList = new ArrayList<Animation>();

		flowers.add((TracedView) findViewById(R.id.s1));
		flowers.add((TracedView) findViewById(R.id.s2));
		flowers.add((TracedView) findViewById(R.id.s3));
		flowers.add((TracedView) findViewById(R.id.s4));
		flowers.add((TracedView) findViewById(R.id.s5));
		flowers.add((TracedView) findViewById(R.id.s6));
		flowers.add((TracedView) findViewById(R.id.s7));
		flowers.add((TracedView) findViewById(R.id.s8));
		flowers.add((TracedView) findViewById(R.id.s9));
		flowers.add((TracedView) findViewById(R.id.s10));
		
		TracedView tvv = (TracedView) findViewById(R.id.point_1);
		tvv.setNewPart(true);
		flowers.add(tvv);
		tvv = (TracedView) findViewById(R.id.point_2);
		tvv.setNewPart(true);
		flowers.add(tvv);
		tvv = (TracedView) findViewById(R.id.point_3);
		tvv.setNewPart(true);
		flowers.add(tvv);
		for (TracedView v : flowers) {
			v.setViewAttributes();
		}
		bee = (MovingObject) findViewById(R.id.bee);
		bee.setViewAttributes();
		drawView = (DrawView) findViewById(R.id.imageView1);
		dragC = new DragController(this, _root, bee, flowers, drawView) {

			@Override
			public boolean updateView() {
				// TODO Auto-generated method stub
				return false;
			}
		};

		for (TracedView v : flowers) {
			v.setViewAttributes();
		}

		bee.setViewAttributes();
		// TracedView tvp = flowers.get(4);
		/////////////////////animation
		intializeAnimation();
		

	}

	private void intializeAnimation() {
		View anim = findViewById(R.id.root2);
		ArrayList<TracedView> flowers2=new ArrayList<TracedView>();
		flowers2.add((TracedView) anim.findViewById(R.id.s1));
		flowers2.add((TracedView) anim.findViewById(R.id.s2));
		flowers2.add((TracedView) anim.findViewById(R.id.s3));
		flowers2.add((TracedView) anim.findViewById(R.id.s4));
		flowers2.add((TracedView) anim.findViewById(R.id.s5));
		flowers2.add((TracedView) anim.findViewById(R.id.s6));
		flowers2.add((TracedView) anim.findViewById(R.id.s7));
		flowers2.add((TracedView) anim.findViewById(R.id.s8));
		flowers2.add((TracedView) anim.findViewById(R.id.s9));
		flowers2.add((TracedView) anim.findViewById(R.id.s10));
		
		TracedView tvv = (TracedView) anim.findViewById(R.id.point_1);
		tvv.setNewPart(true);
		flowers2.add(tvv);
		tvv = (TracedView) anim.findViewById(R.id.point_2);
		tvv.setNewPart(true);
		flowers2.add(tvv);
		tvv = (TracedView) anim.findViewById(R.id.point_3);
		tvv.setNewPart(true);
		flowers2.add(tvv);
		for (TracedView v : flowers2) {
			v.setViewAttributes();
		}
		 bee2 = (MovingObject)  anim.findViewById(R.id.bee);
		bee2.setViewAttributes();
		
		for (final TracedView tvp : flowers2) {
			point_form = tvp.topLeftX - bee2.topLeftX;
			point_to = tvp.topLeftY - bee2.topLeftY;
			Animation animation = new TranslateAnimation(tx, point_form, ty,
					point_to);
			tx = point_form;
			ty = point_to;
			animation.setDuration(500);
			animation.setFillAfter(true);
			animation.setAnimationListener(this);
			animationList.add(animation);
		}
		bee2.startAnimation(animationList.get(currentAnimation));
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		currentAnimation++;
		if (currentAnimation < animationList.size())
			bee2.startAnimation(animationList.get(currentAnimation));
		else{
			bee2.setVisibility(View.GONE);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}