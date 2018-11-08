title: MP4文件缓冲
author: zjy
date: 2018-11-08 17:36:43
tags:
---
	.
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .
    .                                         	
                                                
# 情景描述
		
        
        后台上传mp4文件到服务器，通过nginx代理静态资源MP4把访问地址参数提供给app。app用户反应，视频无法加载。或者加载过慢。（在chrome，firefox,360等众多浏览器直接访问mp4访问地址都是秒播）

# mp4文件格式
	
    moov对于MP4来说，就是目录，它是包含着mp4的元数据。moov模块存放着如下信息
       1 视频包括编码等级、分辨率、色域、码率、帧率、位深、时长等等……
       2 音频又包括声道、采样率等音频特有属性
    
    mdat:媒体数据容器。存放mp4大内容的地方。
    
# 加载快慢的原因是（元数据模块的顺序和使用的访问方式）
    
    1 秒播：chrome，firefox,360 很智能，如果读取MP4文件，发现moov box不在文件前部，会直接读取MP4的文件尾部，加载moov box
    2 卡顿：Flash如果读取MP4文件，发现moov box不在文件前部，不会直接读取MP4的文件尾部去寻找moov box，所以Flash要等文件全部下载完，取到文件尾部的moov头，才可以正常播放。
    3 流媒体服务器，即使moov在文件尾部 也会先发moov头出去给CDN或用户，相当于CDN回源，或者用户回源请求到的MP4，已经是moov头在文件头部的了，虽然 这个时候 源站存储的还是moov头在文件尾部的MP4。
	
## 综上所述：所以，如果安卓采用和flash一样的机制来顺序解析mp4文件，如果mp4文件的moov模块在mdat后面。那么就会造成卡顿！！！


# 解决办法：
	
    知道了mp4播放的原理，我们只要保证。moov在mdat前面解析就行了。ps：修改mp4文件大模块的顺序，是不会影响这个文件整体效果的。
    
    方法1：部署流媒体服务器，给安卓提供流媒体的播放地址。不再采用http地址访问MP4。（成本高，复杂，不考虑）
    方法2：修改mp4文件的模块顺序。（修改mp4模块顺序的代码，可百度，nice）
    方法3：安卓端修改解析mp4的顺序，先解析moov模块。（不懂安卓，不考虑）
   

附：修改mp4文件模块的代码(百度不到我也不会选择这个办法对吧 https://blog.csdn.net/lzh838330255/article/details/53286430)

	  package cc.ligu.mvc.common;
      import java.io.*;
      import java.nio.ByteBuffer;
      import java.nio.ByteOrder;
      import java.nio.channels.FileChannel;

      public class QtFastStart {
          public static boolean sDEBUG = false;

          private static void safeClose(Closeable closeable) {
              if (closeable != null) {
                  try {
                      closeable.close();
                  } catch (IOException e) {
                      printe(e, "Failed to close file: ");
                  }
              }
          }

          /* package */
          static long uint32ToLong(int int32) {
              return int32 & 0x00000000ffffffffL;
          }

          /**
           * Ensures passed uint32 value in long can be represented as Java int.
           */
          /* package */
          static int uint32ToInt(int uint32) throws UnsupportedFileException {
              if (uint32 < 0) {
                  throw new UnsupportedFileException("uint32 value is too large");
              }
              return uint32;
          }

          /**
           * Ensures passed uint32 value in long can be represented as Java int.
           */
          /* package */
          static int uint32ToInt(long uint32) throws UnsupportedFileException {
              if (uint32 > Integer.MAX_VALUE || uint32 < 0) {
                  throw new UnsupportedFileException("uint32 value is too large");
              }
              return (int) uint32;
          }

          /**
           * Ensures passed uint64 value can be represented as Java long.
           */
          /* package */
          static long uint64ToLong(long uint64) throws UnsupportedFileException {
              if (uint64 < 0) throw new UnsupportedFileException("uint64 value is too large");
              return uint64;
          }

          private static int fourCcToInt(byte[] byteArray) {
              return ByteBuffer.wrap(byteArray).order(ByteOrder.BIG_ENDIAN).getInt();
          }

          private static void printf(String format, Object... args) {
              if (sDEBUG) System.err.println("QtFastStart: " + String.format(format, args));
          }

          private static void printe(Throwable e, String format, Object... args) {
              printf(format, args);
              if (sDEBUG) e.printStackTrace();
          }

          private static boolean readAndFill(FileChannel infile, ByteBuffer buffer) throws IOException {
              buffer.clear();
              int size = infile.read(buffer);
              buffer.flip();
              return size == buffer.capacity();
          }

          private static boolean readAndFill(FileChannel infile, ByteBuffer buffer, long position) throws IOException {
              buffer.clear();
              int size = infile.read(buffer, position);
              buffer.flip();
              return size == buffer.capacity();
          }

          /* top level atoms */
          private static final int FREE_ATOM = fourCcToInt(new byte[]{'f', 'r', 'e', 'e'});
          private static final int JUNK_ATOM = fourCcToInt(new byte[]{'j', 'u', 'n', 'k'});
          private static final int MDAT_ATOM = fourCcToInt(new byte[]{'m', 'd', 'a', 't'});
          private static final int MOOV_ATOM = fourCcToInt(new byte[]{'m', 'o', 'o', 'v'});
          private static final int PNOT_ATOM = fourCcToInt(new byte[]{'p', 'n', 'o', 't'});
          private static final int SKIP_ATOM = fourCcToInt(new byte[]{'s', 'k', 'i', 'p'});
          private static final int WIDE_ATOM = fourCcToInt(new byte[]{'w', 'i', 'd', 'e'});
          private static final int PICT_ATOM = fourCcToInt(new byte[]{'P', 'I', 'C', 'T'});
          private static final int FTYP_ATOM = fourCcToInt(new byte[]{'f', 't', 'y', 'p'});
          private static final int UUID_ATOM = fourCcToInt(new byte[]{'u', 'u', 'i', 'd'});

          private static final int CMOV_ATOM = fourCcToInt(new byte[]{'c', 'm', 'o', 'v'});
          private static final int STCO_ATOM = fourCcToInt(new byte[]{'s', 't', 'c', 'o'});
          private static final int CO64_ATOM = fourCcToInt(new byte[]{'c', 'o', '6', '4'});

          private static final int ATOM_PREAMBLE_SIZE = 8;

          public static boolean fastStart(InputStream in, FileOutputStream out) throws IOException, MalformedFileException, UnsupportedFileException {
              boolean ret = false;
              FileInputStream inStream = (FileInputStream) in;
              FileChannel infile = null;
              FileChannel outfile= null;
              try {
                  infile = inStream.getChannel();
                  outfile = out.getChannel();
                  return ret = fastStartImpl(infile, outfile);
              } finally {
                  if (!ret) {
                      infile.transferTo(0, infile.size(), outfile);//当转换不成功时（正常是因文件小），直接copy.
      //                out.delete();
                  }else{
      //                in.delete();
                  }
                  safeClose(inStream);
                  safeClose(out);
              }
          }
          /**
           * @param in  Input file.
           * @param out Output file.
           * @return false if input file is already fast start.
           * @throws IOException
           */
          public static boolean fastStart(String in, String out) throws IOException, MalformedFileException, UnsupportedFileException {
              boolean ret = false;
              FileInputStream inStream = null;
              FileOutputStream outStream = null;
              FileChannel infile = null;
              FileChannel outfile= null;
              try {
                  inStream = new FileInputStream(in);
                  infile = inStream.getChannel();
                  outStream = new FileOutputStream(out);
                  outfile = outStream.getChannel();
                  return ret = fastStartImpl(infile, outfile);
              } finally {
                  if (!ret) {
                      infile.transferTo(0, infile.size(), outfile);//当转换不成功时（正常是因文件小），直接copy.
      //                out.delete();
                  }else{
      //                in.delete();
                  }
                  safeClose(inStream);
                  safeClose(outStream);
              }
          }

          private static boolean fastStartImpl(FileChannel infile, FileChannel outfile) throws IOException, MalformedFileException, UnsupportedFileException {
              ByteBuffer atomBytes = ByteBuffer.allocate(ATOM_PREAMBLE_SIZE).order(ByteOrder.BIG_ENDIAN);
              int atomType = 0;
              long atomSize = 0; // uint64_t
              long lastOffset;
              ByteBuffer moovAtom;
              ByteBuffer ftypAtom = null;
              // uint64_t, but assuming it is in int32 range. It is reasonable as int max is around 2GB. Such large moov is unlikely, yet unallocatable :).
              int moovAtomSize;
              long startOffset = 0;
              System.out.println("QtFastStart------"+"开始");
              // traverse through the atoms in the file to make sure that 'moov' is at the end
              while (readAndFill(infile, atomBytes)) {
                  atomSize = uint32ToLong(atomBytes.getInt()); // uint32
                  atomType = atomBytes.getInt(); // representing uint32_t in signed int

                  // keep ftyp atom
                  if (atomType == FTYP_ATOM) {
                      int ftypAtomSize = uint32ToInt(atomSize); // XXX: assume in range of int32_t
                      ftypAtom = ByteBuffer.allocate(ftypAtomSize).order(ByteOrder.BIG_ENDIAN);
                      atomBytes.rewind();
                      ftypAtom.put(atomBytes);
                      if (infile.read(ftypAtom) < ftypAtomSize - ATOM_PREAMBLE_SIZE) break;
                      ftypAtom.flip();
                      startOffset = infile.position(); // after ftyp atom
                      System.out.println("QtFastStart---FTYP_ATOM---atomType:"+atomType);
                      System.out.println("QtFastStart---FTYP_ATOM---atomType:"+String.valueOf(infile.position()));
                  } else {
      //                System.out.println("QtFastStart------atomType:"+atomType);
                      if (atomSize == 1) {
                          /* 64-bit special case */
                          atomBytes.clear();
                          if (!readAndFill(infile, atomBytes)) break;
                          atomSize = uint64ToLong(atomBytes.getLong()); // XXX: assume in range of int64_t
                          infile.position(infile.position() + atomSize - ATOM_PREAMBLE_SIZE * 2); // seek
                          System.out.println("QtFastStart--atomSize == 1----atomType:"+atomType);
                      } else {
                          infile.position(infile.position() + atomSize - ATOM_PREAMBLE_SIZE); // seek
                          System.out.println("QtFastStart--else----atomType:"+atomType);
                          System.out.println("QtFastStart--else----atomType:"+String.valueOf(infile.position() + atomSize - ATOM_PREAMBLE_SIZE));
                      }
                  }
                  if (sDEBUG) printf("%c%c%c%c %10d %d",
                          (atomType >> 24) & 255,
                          (atomType >> 16) & 255,
                          (atomType >> 8) & 255,
                          (atomType >> 0) & 255,
                          infile.position() - atomSize,
                          atomSize);
                  if ((atomType != FREE_ATOM)
                          && (atomType != JUNK_ATOM)
                          && (atomType != MDAT_ATOM)
                          && (atomType != MOOV_ATOM)
                          && (atomType != PNOT_ATOM)
                          && (atomType != SKIP_ATOM)
                          && (atomType != WIDE_ATOM)
                          && (atomType != PICT_ATOM)
                          && (atomType != UUID_ATOM)
                          && (atomType != FTYP_ATOM)) {
                      printf("encountered non-QT top-level atom (is this a QuickTime file?)");
                      break;
                  }

              /* The atom header is 8 (or 16 bytes), if the atom size (which
               * includes these 8 or 16 bytes) is less than that, we won't be
               * able to continue scanning sensibly after this atom, so break. */
                  if (atomSize < 8)
                      break;
              }
              System.out.println("QtFastStart------"+"第一循环结束atomType:"+atomType);
              if (atomType != MOOV_ATOM) {
                  printf("last atom in file was not a moov atom");
                  return false;
              }
              // moov atom was, in fact, the last atom in the chunk; load the whole moov atom

              // atomSize is uint64, but for moov uint32 should be stored.
              // XXX: assuming moov atomSize <= max vaue of int32
              moovAtomSize = uint32ToInt(atomSize);
              lastOffset = infile.size() - moovAtomSize; // NOTE: assuming no extra data after moov, as qt-faststart.c
              moovAtom = ByteBuffer.allocate(moovAtomSize).order(ByteOrder.BIG_ENDIAN);
              if (!readAndFill(infile, moovAtom, lastOffset)) {
                  throw new MalformedFileException("failed to read moov atom");
              }

              // this utility does not support compressed atoms yet, so disqualify files with compressed QT atoms
              if (moovAtom.getInt(12) == CMOV_ATOM) {
                  throw new UnsupportedFileException("this utility does not support compressed moov atoms yet");
              }

              // crawl through the moov chunk in search of stco or co64 atoms
              while (moovAtom.remaining() >= 8) {
                  int atomHead = moovAtom.position();
                  atomType = moovAtom.getInt(atomHead + 4); // representing uint32_t in signed int
                  if (!(atomType == STCO_ATOM || atomType == CO64_ATOM)) {
                      moovAtom.position(moovAtom.position() + 1);
                      continue;
                  }
                  atomSize = uint32ToLong(moovAtom.getInt(atomHead)); // uint32
                  if (atomSize > moovAtom.remaining()) {
                      throw new MalformedFileException("bad atom size");
                  }
                  moovAtom.position(atomHead + 12); // skip size (4 bytes), type (4 bytes), version (1 byte) and flags (3 bytes)
                  if (moovAtom.remaining() < 4) {
                      throw new MalformedFileException("malformed atom");
                  }
                  // uint32_t, but assuming moovAtomSize is in int32 range, so this will be in int32 range
                  int offsetCount = uint32ToInt(moovAtom.getInt());
                  if (atomType == STCO_ATOM) {
                      printf("patching stco atom...");
                      if (moovAtom.remaining() < offsetCount * 4) {
                          throw new MalformedFileException("bad atom size/element count");
                      }
                      for (int i = 0; i < offsetCount; i++) {
                          int currentOffset = moovAtom.getInt(moovAtom.position());
                          int newOffset = currentOffset + moovAtomSize; // calculate uint32 in int, bitwise addition
                          // current 0xffffffff => new 0x00000000 (actual >= 0x0000000100000000L)
                          if (currentOffset < 0 && newOffset >= 0) {
                              throw new UnsupportedFileException("This is bug in original qt-faststart.c: "
                                      + "stco atom should be extended to co64 atom as new offset value overflows uint32, "
                                      + "but is not implemented.");
                          }
                          moovAtom.putInt(newOffset);
                      }
                  } else if (atomType == CO64_ATOM) {
                      printf("patching co64 atom...");
                      if (moovAtom.remaining() < offsetCount * 8) {
                          throw new MalformedFileException("bad atom size/element count");
                      }
                      for (int i = 0; i < offsetCount; i++) {
                          long currentOffset = moovAtom.getLong(moovAtom.position());
                          moovAtom.putLong(currentOffset + moovAtomSize); // calculate uint64 in long, bitwise addition
                      }
                  }
              }
              infile.position(startOffset); // seek after ftyp atom

              if (ftypAtom != null) {
                  // dump the same ftyp atom
                  printf("writing ftyp atom...");
                  ftypAtom.rewind();
                  outfile.write(ftypAtom);
              }

              // dump the new moov atom
              printf("writing moov atom...");
              moovAtom.rewind();
              outfile.write(moovAtom);

              // copy the remainder of the infile, from offset 0 -> (lastOffset - startOffset) - 1
              printf("copying rest of file...");
              infile.transferTo(startOffset, lastOffset - startOffset, outfile);
              System.out.println("QtFastStart------"+"处理完成");
              return true;
          }

          public static class QtFastStartException extends Exception {
              private QtFastStartException(String detailMessage) {
                  super(detailMessage);
              }
          }

          public static class MalformedFileException extends QtFastStartException {
              private MalformedFileException(String detailMessage) {
                  super(detailMessage);
              }
          }

          public static class UnsupportedFileException extends QtFastStartException {
              private UnsupportedFileException(String detailMessage) {
                  super(detailMessage);
              }
          }


          public static void main(String[] args) {
              try {
                  String path = "C:\\Users\\shenyy\\Desktop\\待转换视频\\1540710345332.mp4";
                  InputStream in = new FileInputStream(path);
                  FileOutputStream out = new FileOutputStream(path+"_");
                  boolean success = QtFastStart.fastStart(in, out);
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (MalformedFileException e) {
                  e.printStackTrace();
              } catch (UnsupportedFileException e) {
                  e.printStackTrace();
              }
          }

      }

附：视频转换前后的模块顺序对比 1前。2后

1 ![upload successful](/paste/pasted-12.png)

2 ![upload successful](/paste/pasted-13.png)
很明显，同一个文件，模块顺序变了，使用flash播放，moov在前的明显秒播。

# 效果

![upload successful](/paste/xiaoguo.gif)
	
附 mp4文件详细格式
![upload successful](/paste/pasted-10.png)