package top.pdcasystem.pdcasystem.Util;

import javafx.scene.shape.TriangleMesh;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChooseWordUtil {
    private static final Logger logger = LoggerFactory.getLogger(ChooseWordUtil.class);

    private static  final String REPLACE = "***";

    private TireNode rootNode = new TireNode();

    @PostConstruct
    public  void init(){
        try (
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensiline.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ){
            String keyword;
            while((keyword = reader.readLine()) != null) {
                this.addKeyword(keyword);
            }
        }catch (IOException e){
            logger.error("加载文本文件失败");
        }
    }

    private  void addKeyword(String keyword) {
        TireNode tireNode = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TireNode subNode = tireNode.getSubNode(c);

            if (subNode == null) {
                subNode = new TireNode();
                tireNode.addSubNode(c, subNode);
            }

            tireNode = subNode;

            if (i == keyword.length() - 1) {
                tireNode.setKeyworEnd(true);
            }
        }
    }

    //发现核心信息
    public Map<String, String> proceeing(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String content = "";

        //指针1
        TireNode temp = rootNode;
        //指针2
        int begin = 0;
        //指针3
        int position = 0;



        while (position < text.length()) {
            char c = text.charAt(position);

            //检查下节点
            temp = temp.getSubNode(c);
            if (temp == null){
                //以begin开头得不是选中词
                sb.append(text.charAt(begin));
                position = ++begin;
                //重指向根节点
                temp = rootNode;
            }else if (temp.isKeyworEnd()) {
                //进入下一个位置
                begin = ++position;
                temp = rootNode;
                //发现定义词了
                content = text.substring(position);
                handle(map, content);

                return map;
            }else {
                // 检查下一个字符
                if (position < text.length() - 1) {
                    position++;
                } else if (position == text.length() - 1) {
                    sb.append(text.charAt(begin));
                    position = ++begin;
                    temp = rootNode;
                }
            }
        }

        map.put("content","");
        return map;
    }

    private void handle(Map<String, String> map, String text){
        String content = StringUtils.substringBefore(text, "。");
        map.put("content",content);
        String[] split = content.split("/");
        if (split.length < 2) {
            System.out.println("content to output achievement have problem");
        }
        if (split.length >= 2) {
            map.put("locate", split[0]);
            map.put("title", split[1]);
        }
        if (split.length == 3) {
            map.put("comment", split[2]);
        }
        if (split.length == 4) {
            map.put("achievement", split[3]);
        }
    }

    private boolean isSymbol(Character c){
        //0x2E80 || c > 0x9FFF 东亚文字
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    private  class  TireNode {
        //结束
        private  boolean isKeyworEnd = false;

        //子节点
        private Map<Character, TireNode> subNodes = new HashMap<>();

        public boolean isKeyworEnd() {
            return isKeyworEnd;
        }

        public void setKeyworEnd(boolean end) {
            isKeyworEnd = end;
        }

        public void addSubNode(Character c, TireNode node) {
            subNodes.put(c, node);
        }

        public TireNode getSubNode(Character c) {
            return subNodes.get(c);
        }

    }
}
