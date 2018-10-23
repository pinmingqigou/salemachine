package com.freeme.freemelite.router.payload;

import android.text.TextUtils;

import com.freeme.freemelite.router.RouterConfig;

import java.util.List;

// FIXME generate failure  field _$Header98
public class RenderCardModel implements BaseModel{

    public static final String TEXT_CARD = "TextCard";
    public static final String LIST_CARD = "ListCard";
    public static final String Standard_CARD = "StandardCard";
    public static final String IMAGE_LIST_CARD = "ImageListCard";
    private PayloadBean payload;

    @Override
    public int getConversationType() {
        if (TextUtils.equals(payload.type,TEXT_CARD)){
            return RouterConfig.CoversationType.TEXT_CARD;
        }else if (TextUtils.equals(payload.type,LIST_CARD)){
            return RouterConfig.CoversationType.LIST_CARD;
        }else if (TextUtils.equals(payload.type,Standard_CARD)){
            return RouterConfig.CoversationType.STANDARD_CARD;
        }else if (TextUtils.equals(payload.type,IMAGE_LIST_CARD)){
            return RouterConfig.CoversationType.IMAGE_LIST_CARD;
        }else {
            return -1;
        }
    }

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }

    public static class headerBean {
        /**
         * namespace : ai.dueros.device_interface.screen
         * name : RenderCard
         * dialogRequestId : f7123fd9-a648-429e-9b02-eae60e679c28
         * messageId : NWI5ZjI1ZjM5Yzc5ZDkyMjk=
         */

        private String namespace;
        private String name;
        private String dialogRequestId;
        private String messageId;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDialogRequestId() {
            return dialogRequestId;
        }

        public void setDialogRequestId(String dialogRequestId) {
            this.dialogRequestId = dialogRequestId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }
    }

    public static class PayloadBean {
        /**
         * type : TextCard
         * image : {"src":"http://xiaodu.baidu.com/img/pic?pic_id=113865491"}
         * title : 上海市今天小雨
         * content : 欢迎进入智能导购
         * link : {"url":"https://m.baidu.com/from=2001a/s?word=ä¸\u008aæµ·å¸\u0082å¤©æ°\u0094","anchorText":"查看更多"}
         * token : eyJib3RfaWQiOiJlZDRmN2Y5NC1mZWUwLWNlY2YtMzQ0ZS1mNDc5MmQ5NDQwNmEiLCJyZXN1bHRfdG9rZW4iOiIyOGZkYWQ2Zjk2OGUwMjQ2ZjQ0ZGZjODc2NzkzZTM2MCIsImJvdF90b2tlbiI6ImRlZTNiMDQ0LTdkNjMtNDdkNy1hODdiLTRkMjhmMGY5NWY5OSJ9
         * list : [{"image":{"src":"http://dbp-resource.gz.bcebos.com/ed4f7f94-fee0-cecf-344e-f4792d94406a/ai_guide_icon.png?authorization=bce-auth-v1/a4d81bbd930c41e6857b989362415714/2018-09-06T06:07:01Z/-1//16927c417355bb40cb810fe87afbcf2634f94a386781003d8ce93090fa036262"},"title":"红双喜","content":"红双喜"},{"image":{"src":"http://dbp-resource.gz.bcebos.com/ed4f7f94-fee0-cecf-344e-f4792d94406a/ai_guide_icon.png?authorization=bce-auth-v1/a4d81bbd930c41e6857b989362415714/2018-09-06T06:07:01Z/-1//16927c417355bb40cb810fe87afbcf2634f94a386781003d8ce93090fa036262"},"title":"长白山","content":"长白山"},{"image":{"src":"http://dbp-resource.gz.bcebos.com/ed4f7f94-fee0-cecf-344e-f4792d94406a/ai_guide_icon.png?authorization=bce-auth-v1/a4d81bbd930c41e6857b989362415714/2018-09-06T06:07:01Z/-1//16927c417355bb40cb810fe87afbcf2634f94a386781003d8ce93090fa036262"},"title":"中华","content":"中华"},{"image":{"src":"http://dbp-resource.gz.bcebos.com/ed4f7f94-fee0-cecf-344e-f4792d94406a/ai_guide_icon.png?authorization=bce-auth-v1/a4d81bbd930c41e6857b989362415714/2018-09-06T06:07:01Z/-1//16927c417355bb40cb810fe87afbcf2634f94a386781003d8ce93090fa036262"},"title":"利群","content":"利群"},{"image":{"src":"http://dbp-resource.gz.bcebos.com/ed4f7f94-fee0-cecf-344e-f4792d94406a/ai_guide_icon.png?authorization=bce-auth-v1/a4d81bbd930c41e6857b989362415714/2018-09-06T06:07:01Z/-1//16927c417355bb40cb810fe87afbcf2634f94a386781003d8ce93090fa036262"},"title":"南京","content":"南京"}]
         */

        private String type;
        private ImageBean image;
        private String title;
        private String content;
        private LinkBean link;
        private String token;
        private List<ListBean> list;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LinkBean getLink() {
            return link;
        }

        public void setLink(LinkBean link) {
            this.link = link;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ImageBean {
            /**
             * src : http://xiaodu.baidu.com/img/pic?pic_id=113865491
             */

            private String src;

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }

        public static class LinkBean {
            /**
             * url : https://m.baidu.com/from=2001a/s?word=ä¸æµ·å¸å¤©æ°
             * anchorText : 查看更多
             */

            private String url;
            private String anchorText;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAnchorText() {
                return anchorText;
            }

            public void setAnchorText(String anchorText) {
                this.anchorText = anchorText;
            }
        }

        public static class ListBean {
            /**
             * image : {"src":"http://dbp-resource.gz.bcebos.com/ed4f7f94-fee0-cecf-344e-f4792d94406a/ai_guide_icon.png?authorization=bce-auth-v1/a4d81bbd930c41e6857b989362415714/2018-09-06T06:07:01Z/-1//16927c417355bb40cb810fe87afbcf2634f94a386781003d8ce93090fa036262"}
             * title : 红双喜
             * content : 红双喜
             */

            private ImageBeanX image;
            private String title;
            private String content;

            public ImageBeanX getImage() {
                return image;
            }

            public void setImage(ImageBeanX image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public static class ImageBeanX {
                /**
                 * src : http://dbp-resource.gz.bcebos.com/ed4f7f94-fee0-cecf-344e-f4792d94406a/ai_guide_icon.png?authorization=bce-auth-v1/a4d81bbd930c41e6857b989362415714/2018-09-06T06:07:01Z/-1//16927c417355bb40cb810fe87afbcf2634f94a386781003d8ce93090fa036262
                 */

                private String src;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }
            }
        }
    }
}
