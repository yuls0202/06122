package sor;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IM im = new IM();
 
        while (true) {
            System.out.println("==== 재고관리시스템  ====");
            System.out.println("1>  상품 등록");
            System.out.println("2>  상품조회");
            System.out.println("3>  상품검색");
            System.out.println("4>  상품삭제");
            System.out.println("5>  종료");
 
            int menu = Integer.parseInt(sc.nextLine());
            if (menu == 1) {
                System.out.println("=== 상품등록 ===");
                System.out.print("> 상품명 : ");
                String imP = sc.nextLine();
                System.out.print("> 상품설명 : ");
                String imPInfo = sc.nextLine();
                try {
                    int result = im.insert(imP, imPInfo);
                    if (result>0) {
                        System.out.println("성공");
                    }else {
                        System.out.println("실패");
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }            
            }else if (menu == 2) {
                System.out.println("=== 상품조회 ===");
                try {
                    List <HashMap<String, Object>> hachList = im.inquiry();
                    for (HashMap<String,Object> hashm : hachList) {
                        System.out.println(
                                hashm.get("imNo") + "\t"+ hashm.get("imP") + "\t"+ hashm.get("imPInfo") + "\t"+    hashm.get("imDate")
                        );
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }else if (menu == 3) {
                System.out.println("=== 상품검색 ===");
                System.out.println("> 상품이름을 입력해주세요 : ");
                String Idck = sc.nextLine();
                try {
                    List <Product> isckList = im.isIdExist(Idck);
                    System.out.println("번호 \t 제품명 \t 상세내용 \t 입력날짜");
                    for(Product isckname : isckList) {
                        System.out.println(
                            isckname.getNo()+ "\t" + isckname.getImP()+ "\t" + isckname.getImPInfo()+ "\t" + isckname.getDate()
                        );
                    }
                    
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }else if (menu == 4) {
                System.out.println("=== 상품삭제 ===");
                System.out.println("> 상품id를 입력해주세요 : ");
                int imNo = Integer.parseInt(sc.nextLine());
                try {
                    int result = im.delete(imNo);
                    if (result>0) {
                        System.out.println("성공");
                    }else {
                        System.out.println("실패");
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
 
            }else if (menu == 5) {
                System.out.println("=== 프로그램이 종료됩니다. ===");
                System.exit(0);
            }else {
                System.out.println("1~4까지의 숫자를 입력해주세요.");
            }
        }        
    }
}