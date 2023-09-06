
cmake_minimum_required(VERSION 3.22.1)

project("mpeg")

add_library(
        mpeg
        SHARED
        mpeg.cpp)


# 1. 定义库别名，方面后面使用
set(ffmpeg_lib_dir ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

# 2. 引入头文件目录
include_directories(${PROJECT_SOURCE_DIR}/include)


# 导入 ffmpeg 相关的动态库
# 1、导入 avcodec
# add_library
# 参数1：库的名称
# 参数2：表示共享库
# 参数3：表示导入
add_library(avcodec
        SHARED
        IMPORTED)
# set_target_properties
# 参数1：库的名称
# 参数2：表示设置路径的属性，就是导入本地路径
# 参数3：本地路径
set_target_properties(avcodec
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavcodec.so)

# 2、导入 avdevice
add_library(avdevice SHARED IMPORTED)
set_target_properties(avdevice
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavdevice.so)

# 3、导入 avfilter
add_library(avfilter SHARED IMPORTED)
set_target_properties(avfilter
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavfilter.so)

# 4、导入 avformat
add_library(avformat SHARED IMPORTED)
set_target_properties(avformat
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavformat.so)

# 5、导入 avutil
add_library(avutil SHARED IMPORTED)
set_target_properties(avutil
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavutil.so)

# 6、导入 postproc
add_library(postproc SHARED IMPORTED)
set_target_properties(postproc
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libpostproc.so)

# 7、导入 swresample
add_library(swresample SHARED IMPORTED)
set_target_properties(swresample
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libswresample.so)

# 8、导入 swscale
add_library(swscale SHARED IMPORTED)
set_target_properties(swscale
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libswscale.so)


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        mpeg

        avcodec
        avdevice
        avfilter
        avformat
        avutil
        postproc
        swresample
        swscale
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})