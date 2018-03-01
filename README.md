# weixin_backend
用JAVA(SPRINGBOOT)实现的微信小程序后台，有好多微信小程序是用来做内容分类的，感觉做一个微信小程序后台，就可以统一接入这类微信小程序
## 接口规范
```
request: 
  key1=value1&key2=value2&key3=value3&.....
respones:
    {
        code: 错误码,0 成功；其他 错误码,
        msg: "对应的错误描述",
		data: "json 报文"
    }
```
## 用户接口

### 1. 用户登陆

#### URL: `POST /weixin_backend/user/userLogin`
#### REQUEST参数

| 参数 | 参数类型 | 必须 |参数位置|参数说明 | 
|  -------- | :----- :| :----- : |:----- : | 
| appId | String |Y | body| 内部应用标识 | 
| encryptedData | String | Y | body |  微信小程序登陆微信后，获取的用户信息密文|
| iv | String | Y | body | AES加密算法的偏移量参数 |
| code |String | Y | body | 微信小程序的登陆凭证 |


#### RESPONSE参数说明

| 属性 | 类型 |  说明 | 
| -------- | :----- : | 
| token | String | 内部用户标识（后续改成token) |
| nickName | String | 昵称 |
| gender | String | 性别（'1','2'） |
| country | String | 国家或地区 |
| province | String | 省份 |
| city | String | 城市 |
| avatarUrl | String | 头像地址 |

```
{
  "token": "100100000000000008",
  "nickName": "Henry",
  "gender": "1",
  "country": "CN",
  "province": "guangdong",
  "city": "shenzhen",
  "avatarUrl": "https://"
}
```

