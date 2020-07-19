package me.emilelu.doriemu;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Music {
	public static void main(String[] args) throws Exception, IOException {
		int fuck = 1;
		while (fuck == 1) {

			AudioInputStream audioInputStream;// 文件流
			AudioFormat audioFormat;// 文件格式
			SourceDataLine sourceDataLine;// 输出设备
			File file = new File("src/me/emilelu/doriemu/bgm.mp3");

			// 取得文件输入流
			audioInputStream = AudioSystem.getAudioInputStream(file);
			audioFormat = audioInputStream.getFormat();
			// 转换mp3文件编码
			if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audioFormat.getSampleRate(), 16,
						audioFormat.getChannels(), audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
				audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
			}

			// 打开输出设备
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat,
					AudioSystem.NOT_SPECIFIED);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			byte tempBuffer[] = new byte[320];
			try {

				int cnt;
				// 读取数据到缓存数据
				while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
					if (cnt > 0) {
						// 写入缓存数据
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				// Block等待临时数据被输出为空
				sourceDataLine.drain();
				sourceDataLine.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}

		}
	}
}