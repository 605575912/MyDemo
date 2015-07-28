//package com.lzxmy.demo.upload;
//
//import java.io.FilterOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
//import org.apache.commons.httpclient.methods.multipart.Part;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//
//import com.lzxmy.demo.upload.ApiClient.ProgressListener;
//
//
//
//public class ProgressEntity extends MultipartRequestEntity {
//	ProgressListener listener;
//
//	public ProgressEntity(Part[] parts, HttpMethodParams params,
//			ProgressListener listener) {
//		super(parts, params);
//		this.listener = listener;
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void writeRequest(OutputStream out) throws IOException {
//		// TODO Auto-generated method stub
//		super.writeRequest(new CountingOutputStream(out, this.listener));
//
//	}
//
//	public class CountingOutputStream extends FilterOutputStream {
//		private final ProgressListener listener;
//		private long transferred;
//		CountingOutputStream(final OutputStream out,
//				final ProgressListener listener) {
//			super(out);
//			this.listener = listener;
//			this.transferred = 0;
//		}
//
//		@Override
//		public void write(final byte[] b, final int off, final int len)
//				throws IOException {
//			// NO, double-counting, as super.write(byte[], int, int)
//			// delegates to write(int).
//			// super.write(b, off, len);
//
//			out.write(b, off, len);
//			this.transferred += len;
//			if (listener != null) {
//				this.listener.transferred(this.transferred);
//			}
//
//		}
//
//		@Override
//		public void write(final int b) throws IOException {
//			out.write(b);
//			this.transferred++;
//			if (listener != null) {
//				this.listener.transferred(this.transferred);
//			}
//		}
//	}
//}
