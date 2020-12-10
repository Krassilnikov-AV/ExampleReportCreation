<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // распознавание русского алфавита на JSP.
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Raspisanie</title>
</head>

<body>
    <h2>Создание отчётов</h2>
    <form name="inserting.jsp" action="UploadServlet" method="POST" enctype="multipart/form-data">
        <table width="400" border="0" cellpadding="3" cellspacing="0">
            <col width="100" valign="top">
            <col width="250" valign="top">
            <tbody>
                <tr><td><b>Вид отчёта</b></td></tr>
                <!--                <tr><td><input name="dzen" type="radio" value="nedzen"> Расписание</td></tr>
                                <tr><td><input name="dzen" type="radio" value="dzen"> Внештатные сотрудники</td></tr>          -->
                <!---Другой вариант выбора отчета--->
                <tr><td>
                        <p><select  size="1" required>
                                <option disabled>Выберите вид отчёта</option>
                                <option value="Расписание">Расписание занятий</option>
                                <option selected value="Внештатные сотрудники">Внештатные сотрудники</option>                              
                            </select></p>
                    </td></tr>
                <tr>
                    <td><input type="file" name="fileToUpload" size="57" /></BR></BR></td>                   
                </tr>
                <tr><td><input type="submit" value="загрузить" name="upload" ></p></BR></td></tr>
                <tr> <td>
                    <p><input type="text" name="pathToSave" size="57" > 
                     <input type="submit" value="Найти" name="searh" ></p></BR>
                    </td> </tr>
                <tr><td>
                        <p>Поиск группы: <input type="text" name="last" value="" size="30" >
                            <input type="submit" value="Получить данные" name="data" ></p>
                    </td></tr>
                <tr><td>
                        <p>Выберите месяц: <input type="month" name="calendar">
                            <input type="submit" value="Выбрать"></p>
                        <p>Результаты поиска: </p>  
                    </td></tr>

                <tr>
                    <td><textarea name="listData" rows="15" cols="100"> </textarea></td>                
                </tr>
                <tr><td><p><input type="submit" value="Выбрать" name="select" >
                            <input type="submit" value="Удалить" name="delete" >
                        </p></td></tr>
                <tr><td><p><input type="submit" value="Создать_отчёт" name="create"></p></td></tr>
                </form> 
            </tbody>
        </table>
    </form>
</body>