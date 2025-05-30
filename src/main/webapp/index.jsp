<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>URL Shortener</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding-top: 50px; background-color: #f8f9fa; }
        .card { margin-bottom: 20px; box-shadow: 0 0.125rem 0.25rem rgba(0,0,0,0.075); }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1 class="text-center mb-4">URL Shortener</h1>

            <div class="card">
                <div class="card-header bg-primary text-white">
                    Create Short URL
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/create" method="post">
                        <div class="mb-3">
                            <label for="originalUrl" class="form-label
