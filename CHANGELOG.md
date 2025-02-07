# 变更日志

所有版本变更、缺陷修复都将记录在该文档

- 该文件模板位于 [维护变更日志](https://keepachangelog.com/zh-CN/1.0.0/)

- 版本号规范位于 [语义化版本 2.0.0](https://semver.org/spec/v2.0.0.html)

---

## [待发布]

## [1.3.0] - 待定

### Added

- 复核: 批次号扫描时候出现已扫描数量的占比。
- 收货: 收货时，如果收货商品状态为破损时，可填写损坏数量，换大箱和换小箱数量
- 库存查询:    显示入库车辆信息 和是否扫码字段
- 拣货
  - 拣货增加库位商品数量，拣货一托，数量同减，新增ERP发运号
  - 拣货人员有修改权限（可以修改托数和数量和库位）

### Optimize

- 权限问题
  - 每个叉车只能看到自己的拣货单
  - 每个保管只能看到自己的收货单、复核单
- 拣货
  - 侧表头去掉吨位、方数

---

## [1.2.0] - 2022年04月2日

### Added

- 任务中心功能页面添加搜索框

## [1.1.0] - 2022年04月2日

### Added

- 复核装车流程合并，屏蔽装车按钮
- 拣货后，按拖下发只复核

## [已发布]

## [1.0.3] - 2022年02月23日

### Added

- 装车、复核任务数量默认填充为计划数量

### Fixed

- bug#41937

## [1.0.2] - 2022年01月17日

### Added

- 收货默认选中 "合格" 状态
- 收货登记 - 增加三个新字段：是否扫码、备注、扫码比例
- PDA 增加 OCR 识别

### Fixed

- bug#39167/38955/38956

## [1.0.1] - 2021年10月22日

### Added

- 装车确认上报托数
- 更换应用名称

### Optimize

- 解决 64K 问题
- jdk 提升至 java11
- 调整界面跳转两次的问题

## [1.0.0] - 2021年07月29日

### Added

- 登录功能
  - 选择库位
- 首页
  - 收货
    - 收货列表
    - 收货详情
    - 收货登记
  - 拣货（待定，可能独立 APP）
  - 移位
    - 扫描移出库位
    - 扫描移位商品
    - 移位商品详情
    - 移位确认
  - 库存查询
  - 装车
    - 装车列表
    - 装货详情
    - 装车确认
- 任务中心
  - 主管权限
  - 保管员权限
- 我的
  - 切换仓库
  - 修改密码
