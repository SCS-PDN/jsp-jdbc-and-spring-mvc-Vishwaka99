<!DOCTYPE html>
<html>
<head>
    <title>Available Courses</title>
</head>
<body>
    <h2>Available Courses</h2>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>

    <c:if test="${empty courses}">
        <p>No courses available at the moment.</p>
    </c:if>

    <c:if test="${not empty courses}">
        <table border="1">
            <tr>
                <th>Course Name</th>
                <th>Instructor</th>
                <th>Credits</th>
                <th>Action</th>
            </tr>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td>${course.name}</td>
                    <td>${course.instructor}</td>
                    <td>${course.credits}</td>
                    <td>
                        <form action="register/${course.course_id}" method="post">
                            <button type="submit">Register</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
