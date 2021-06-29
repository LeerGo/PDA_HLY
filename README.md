### 恒利源 WMS 项目

#### 联调问题

1. 公共
   
   - 列表页面刷新问题
     
     - 确认后返回上一页的数据同步，考虑使用 diffutils
   
   - 涉及到检索功能的详情页面

2. 问题模块
   
   - [ ] 收货
     
     - **解决** 收货确认，传入的 code 和返回数据的 code 不一致 @szy
       
       ```
       http://192.168.30.181/wms/pda/receive/register?receiveCode=SH202106260016&receiveItemCode=1ed04ed330814408a9eaadd11e5b7544
       1ed04ed330814408a9eaadd11e5b7544 对应的是学生巧克力奶，返回的确实黑谷牛奶
       ```
     
     - 待续  
   
   - [ ] 装车
     
     - **解决** 装车确认，服务器 500 @xbx 
       
       ```json
       URL: http://192.168.30.181/wms/pda/outbound/loadingCarConfirm
       请求：
       {
          "code": "CK202106280002",
          "outboundItemDTOS": [
             {
                "code": "ffe4b0349e1d4a2a91f06a3575fbd5a5",
                "loadingCarQuantity": 2
             }
          ]
       }
       响应：
       {
          "status": 500
       }
       ```
     
     - 待续
   
   - [ ] 移位
     
     - **解决** A1 库位没有商品 @xbx
     - **解决** 移位确认详情加载有问题 @szy
   
   - [ ] 任务中心
     
     - **解决** 无法分配
       
       ```json
       分配保管员：
       http://192.168.31.166/wms/pda/tasks/assignPDA
       请求:
       {
          "orderCodes": [
             "SH20210626002501"
          ],
          "partyCodeList": [
             {
                "partyCode": "1",
                "partyName": "保管员1"
             }
          ],
          "workerType": "CUSTODIAN"
       }
       响应："msg": "数据库操作异常，请联系管理员",
       ```
     
     - 待续
   
   - [ ] 复核
     
     - **解决** 复核确认没有接入 @szy
     
     - **解决** 部分数量复核直接变成了已复核状态 @szy
     
     - 部分复核，比如 10 个复核 1 个，再进入复核详情，应该变成 9 个才对

3. 待测试模块
   
   - [ ] 修改密码
   
   - [ ] 拣货

4. 通过模块
   
   - [x] 登录
   
   - [x] 选择仓库

#### 资料

蓝湖：

1. 恒利源 PDA: https://lanhuapp.com/web/#/item/project/board?pid=2da812df-972b-4855-9336-c0aeb7d58fb8
   标准仓 PDA: https://lanhuapp.com/web/#/item/project/stage?tid=7161f3f1-b56d-4e23-9c02-d951b841e9cd&pid=8cf95f96-d40b-4352-98fc-540c9f727287

2. API：http://yanqy.oicp.vip/wms/doc.html

3. 原型: file:///Users/Xoder/Company/Arpa/3.WMS/4%20%E6%81%92%E5%88%A9%E6%BA%90/%E8%BF%AD%E4%BB%A3%23210415/%E6%81%92%E5%88%A9%E6%BA%90PDA%E5%8E%9F%E5%9E%8B/index.html#id=jb9e44&p=%E4%BB%BB%E5%8A%A1%E4%B8%AD%E5%BF%83_wms%E4%B8%80%E6%9C%9F_&g=12

4. SSO 登录：https://dreamer192.yuque.com/docs/share/0efdc117-a195-49e9-84a6-effe8e57fbfe?#（密码：sz8f）

#### 后台地址

[中台登录](http://test.58arpa.com:8090/pages/viewpage.action?pageId=30051285)

[WMS 后台登录](http://test.sso.sarpa.cn/sso-server/login?redirect_url=http://192.168.31.24/&source-id=4)

- 账号：test02/test02
