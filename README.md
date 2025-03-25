# RuoYi-Vue-Plus 视频模块

这是RuoYi-Vue-Plus框架的视频模块扩展，提供了HLS(m3u8)视频上传、转码和播放功能。

## 功能特性

- 视频上传：支持MP4、AVI、RMVB等常见视频格式上传
- 视频转码：自动将上传的视频转换为HLS(m3u8)格式，提高流畅度和兼容性
- 视频封面：自动从视频提取封面
- 视频播放：基于video.js的视频播放器，支持HLS(m3u8)视频播放
- 视频管理：提供完整的CRUD操作

## 技术实现

- 后端：基于RuoYi-Vue-Plus框架
- 视频处理：使用FFmpeg进行视频转码和封面提取
- 文件存储：使用框架自带的OSS存储功能
- 前端播放器：基于video.js和videojs-contrib-hls.js

## 使用方法

1. 将`ruoyi-video`模块复制到RuoYi-Vue-Plus项目的`ruoyi-modules`目录下
2. 在`ruoyi-modules/pom.xml`中添加`<module>ruoyi-video</module>`
3. 执行视频模块的`resources/video.sql`脚本创建数据库表和初始菜单
4. 启动项目，访问`/video/player/index?id=视频ID`查看视频播放页面
5. 上传视频可调用`/video/upload`接口

## 注意事项

1. 需要在服务器上安装FFmpeg工具来支持视频处理
2. 视频处理是一个资源密集型操作，在生产环境中应该使用异步任务处理
3. 对于大型系统，可能需要考虑专门的视频处理服务器和CDN加速

## 扩展建议

1. 添加视频分类和标签功能
2. 添加视频评论功能
3. 实现视频处理的异步任务队列
4. 添加视频清晰度选择功能
5. 实现视频切片的CDN分发 