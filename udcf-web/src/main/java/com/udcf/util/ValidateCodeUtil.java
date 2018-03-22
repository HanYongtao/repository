package com.udcf.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.imageio.ImageIO;

public class ValidateCodeUtil {

	// 图片的宽度。
	private int				width			= 160;
	// 图片的高度。
	private int				height			= 40;
	// 验证码字符个数
	private int				codeCount		= 4;
	// 验证码干扰线数
	private int				lineCount		= 15;
	// 验证码
	private String			code			= null;
	// 验证码图片Buffer
	private BufferedImage	buffImg			= null;
	
	private ByteArrayOutputStream tempImg   = null;

	// 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
	private char[]			codeSequence	= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	// 验证码会话名称常量 
	public static String SESSION_VCODE_NAME = "SESSION_VCODE";
	/**
	 * 默认构造函数,设置默认参数
	 */
	public ValidateCodeUtil() {

	}

	/**
	 * @param width 图片宽
	 * @param height 图片高
	 */
	public ValidateCodeUtil(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * @param width
	 *            图片宽
	 * @param height
	 *            图片高
	 * @param codeCount
	 *            字符个数
	 * @param lineCount
	 *            干扰线条数
	 */
	public ValidateCodeUtil(int width, int height, int codeCount, int lineCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
	}
	
	public class ValidateCode {
		private String code  = null;
		private byte[] image = null;
		
		public ValidateCode(String code, byte[] image)
		{
			this.code = code;
			this.image = image;
		}
		
		public String getCode() {
			return code;
		}
		public byte[] getImage() {
			return image;
		}
	}

	public ValidateCode createCode() throws IOException {
		int x = 0, fontHeight = 0, codeY = 0;
		int red = 0, green = 0, blue = 0;

		x = width / (codeCount + 2);// 每个字符的宽度(左右各空出一个字符)
		fontHeight = height - 5;// 字体的高度
		codeY = height - 4;

		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 生成随机数
		SecureRandom random = new SecureRandom();
		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 创建字体,可以修改为其它的
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, fontHeight);
		g.setFont(font);

		for (int i = 0; i < lineCount; i++) {
			// 设置随机开始和结束坐标
			int xs = random.nextInt(width);// x坐标开始
			int ys = random.nextInt(height);// y坐标开始
			int xe = xs + random.nextInt(width / 8);// x坐标结束
			int ye = ys + random.nextInt(height / 8);// y坐标结束

			// 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawLine(xs, ys, xe, ye);
		}

		// randomCode记录随机产生的验证码
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount个字符的验证码。
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			// 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x, codeY);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		// 将四位数字的验证码保存到Session中。
		code = randomCode.toString();
		tempImg = new ByteArrayOutputStream();
		
		ImageIO.write(buffImg, "png", tempImg);
		
		return new ValidateCode(code, tempImg.toByteArray());
	}

	public BufferedImage getImgage() {
		return buffImg;
	}

	public String getCode() {
		return code;
	}
}