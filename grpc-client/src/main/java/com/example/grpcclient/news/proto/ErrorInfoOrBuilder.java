// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: news.proto

package com.example.grpcclient.news.proto;

public interface ErrorInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:news.ErrorInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 错误的业务编码
   * </pre>
   *
   * <code>string errorCode = 1;</code>
   * @return The errorCode.
   */
  java.lang.String getErrorCode();
  /**
   * <pre>
   * 错误的业务编码
   * </pre>
   *
   * <code>string errorCode = 1;</code>
   * @return The bytes for errorCode.
   */
  com.google.protobuf.ByteString
      getErrorCodeBytes();

  /**
   * <pre>
   * 默认提示信息
   * </pre>
   *
   * <code>string defaultMsg = 2;</code>
   * @return The defaultMsg.
   */
  java.lang.String getDefaultMsg();
  /**
   * <pre>
   * 默认提示信息
   * </pre>
   *
   * <code>string defaultMsg = 2;</code>
   * @return The bytes for defaultMsg.
   */
  com.google.protobuf.ByteString
      getDefaultMsgBytes();
}
