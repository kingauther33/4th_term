- Xây dựng một template với jsp thường.
    - jsp: include
    - Truyền biến vào file include đó.
    - Một số lưu ý xử lý lỗi liên quan đến encoding (phông Tiếng Việt).
      - Sửa file web.xml, thêm đoạn cấu hình encoding cho jsp.
    
      -     <jsp-config>
                <jsp-property-group>
                    <url-pattern>*.jsp</url-pattern>
                    <page-encoding>UTF-8</page-encoding>
                </jsp-property-group>
            </jsp-config>
      - Set encoding cho request trong trường hợp truyền tham số có Tiếng Việt
- Điều hướng luồng dữ liệu từ servlet ra jsp và ngược lại.
- Quay lại java kỳ 2. Maven project, tương tác với db (mysql)