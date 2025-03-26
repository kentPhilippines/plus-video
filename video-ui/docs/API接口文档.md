# API接口文档

## 1. 接口规范

### 1.1 请求规范
- 基础URL: `https://api.example.com/v1`
- 请求方式: REST
- 数据格式: JSON
- 字符编码: UTF-8

### 1.2 响应格式
```json
{
  "code": 0,           // 状态码，0表示成功
  "message": "success", // 状态描述
  "data": {            // 响应数据
    // 具体数据
  }
}
```

### 1.3 错误码说明
| 错误码 | 说明 |
|--------|------|
| 0 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器错误 |

## 2. 用户相关接口

### 2.1 用户登录
- 请求路径：`/user/login`
- 请求方式：POST
- 请求参数：
```json
{
  "username": "string", // 用户名
  "password": "string"  // 密码
}
```
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "token": "string",      // 访问令牌
    "userId": "string",     // 用户ID
    "username": "string",   // 用户名
    "avatar": "string"      // 头像URL
  }
}
```

### 2.2 用户注册
- 请求路径：`/user/register`
- 请求方式：POST
- 请求参数：
```json
{
  "username": "string",     // 用户名
  "password": "string",     // 密码
  "email": "string",       // 邮箱
  "phone": "string"        // 手机号
}
```
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "userId": "string",    // 用户ID
    "username": "string"   // 用户名
  }
}
```

## 3. 视频相关接口

### 3.1 获取视频列表
- 请求路径：`/video/list`
- 请求方式：GET
- 请求参数：
```json
{
  "page": 1,           // 页码
  "pageSize": 10,      // 每页数量
  "categoryId": "string", // 分类ID（可选）
  "keyword": "string"    // 搜索关键词（可选）
}
```
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "total": 100,      // 总数
    "list": [{
      "videoId": "string",    // 视频ID
      "title": "string",      // 标题
      "cover": "string",      // 封面图
      "duration": 180,        // 时长（秒）
      "viewCount": 1000,      // 播放次数
      "createTime": "string"  // 创建时间
    }]
  }
}
```

### 3.2 获取视频详情
- 请求路径：`/video/detail/{videoId}`
- 请求方式：GET
- 请求参数：无
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "videoId": "string",     // 视频ID
    "title": "string",       // 标题
    "description": "string", // 描述
    "cover": "string",       // 封面图
    "playUrl": "string",     // 播放地址
    "duration": 180,         // 时长（秒）
    "viewCount": 1000,       // 播放次数
    "likeCount": 100,        // 点赞数
    "commentCount": 50,      // 评论数
    "createTime": "string",  // 创建时间
    "author": {
      "userId": "string",    // 作者ID
      "username": "string",  // 作者名
      "avatar": "string"     // 作者头像
    }
  }
}
```

## 4. 评论相关接口

### 4.1 获取评论列表
- 请求路径：`/comment/list`
- 请求方式：GET
- 请求参数：
```json
{
  "videoId": "string",  // 视频ID
  "page": 1,           // 页码
  "pageSize": 10       // 每页数量
}
```
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "total": 50,       // 总数
    "list": [{
      "commentId": "string",   // 评论ID
      "content": "string",     // 评论内容
      "createTime": "string",  // 创建时间
      "user": {
        "userId": "string",    // 用户ID
        "username": "string",  // 用户名
        "avatar": "string"     // 头像
      }
    }]
  }
}
```

### 4.2 发表评论
- 请求路径：`/comment/create`
- 请求方式：POST
- 请求参数：
```json
{
  "videoId": "string",  // 视频ID
  "content": "string"   // 评论内容
}
```
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "commentId": "string"   // 评论ID
  }
}
```

## 5. 用户收藏接口

### 5.1 收藏视频
- 请求路径：`/favorite/add`
- 请求方式：POST
- 请求参数：
```json
{
  "videoId": "string"  // 视频ID
}
```
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": null
}
```

### 5.2 获取收藏列表
- 请求路径：`/favorite/list`
- 请求方式：GET
- 请求参数：
```json
{
  "page": 1,          // 页码
  "pageSize": 10      // 每页数量
}
```
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "total": 20,      // 总数
    "list": [{
      "videoId": "string",    // 视频ID
      "title": "string",      // 标题
      "cover": "string",      // 封面图
      "duration": 180,        // 时长（秒）
      "createTime": "string"  // 收藏时间
    }]
  }
}
```

## 6. 广告相关接口

### 6.1 获取广告位
- 请求路径：`/ad/position`
- 请求方式：GET
- 请求参数：
```json
{
  "position": "string"  // 广告位置标识
}
```
- 响应参数：
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "adId": "string",       // 广告ID
    "type": "string",       // 广告类型
    "title": "string",      // 广告标题
    "imageUrl": "string",   // 图片URL
    "linkUrl": "string",    // 跳转链接
    "startTime": "string",  // 开始时间
    "endTime": "string"     // 结束时间
  }
}
```

## 7. 接口调用示例

### 7.1 使用 Axios 调用示例
```typescript
import axios from 'axios'

// 创建axios实例
const request = axios.create({
  baseURL: 'https://api.example.com/v1',
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code !== 0) {
      // 处理错误
      return Promise.reject(new Error(res.message))
    }
    return res.data
  },
  error => {
    return Promise.reject(error)
  }
)

// 调用示例
async function getVideoList(params) {
  try {
    const data = await request.get('/video/list', { params })
    return data
  } catch (error) {
    console.error('获取视频列表失败:', error)
    return null
  }
}
```

## 8. 注意事项

### 8.1 安全性
- 所有接口都需要进行签名验证
- 敏感数据需要加密传输
- 防止SQL注入和XSS攻击

### 8.2 性能优化
- 使用缓存减少请求次数
- 分页加载避免一次性加载过多数据
- 图片资源使用CDN加速

### 8.3 错误处理
- 统一的错误处理机制
- 详细的错误日志记录
- 友好的错误提示 