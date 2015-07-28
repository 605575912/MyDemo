package com.lzxmy.demo.wireframe;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author Sodino E-mail:sodinoopen@hotmail.com
 * @version Time：2012-6-27 上午07:32:00
 */
public class RotateAnimation extends Animation {
	/** 值为true时可明确查看动画的旋转方向。 */
	public static final boolean DEBUG = false;
	/** 沿Y轴正方向看，数值减1时动画逆时针旋转。 */
	public static final boolean ROTATE_DECREASE = true;
	/** 沿Y轴正方向看，数值减1时动画顺时针旋转。 */
	public static final boolean ROTATE_INCREASE = false;
	/** Z轴上最大深度。 */
//	public static final float DEPTH_Z = 310.0f;
	/** 动画显示时长。 */
	public static final long DURATION = 1000l;
	/** 图片翻转类型。 */
	private final boolean type;
	private final float centerX;
	private final float centerY;
	private Camera camera;
	/** 用于监听动画进度。当值过半时需更新txtNumber的内容。 */
	private InterpolatedTimeListener listener;

	public RotateAnimation(float cX, float cY, boolean type) {
		centerX = cX;
		centerY = cY;
		this.type = type;
		setDuration(DURATION);
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		// 在构造函数之后、getTransformation()之前调用本方法。
		super.initialize(width, height, parentWidth, parentHeight);
		camera = new Camera();
	}

	public void setInterpolatedTimeListener(InterpolatedTimeListener listener) {
		this.listener = listener;
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation transformation) {
		// interpolatedTime:动画进度值，范围为[0.0f,10.f]
		final Matrix matrix = transformation.getMatrix();
		
		camera.save();
		camera.translate(0.0f, 0.0f, 0.0f);
		camera.rotateY(80);
		camera.getMatrix(matrix);
		camera.restore();
//			//确保图片的翻转过程一直处于组件的中心点位置
			matrix.preTranslate(-34, -34);
//			matrix.postTranslate(34, 34);
//		}
	}

	/** 动画进度监听器。 */
	public static interface InterpolatedTimeListener {
		public void interpolatedTime(float interpolatedTime);
	}
}