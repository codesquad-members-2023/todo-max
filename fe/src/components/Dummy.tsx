import React, { useState } from 'react';
import { Button } from './buttons/Button';

export function Dummy() {
  const [inputValue, setInputValue] = useState('');

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  const isInputEmpty = inputValue.length === 0;

  return (
    <>
      {/* input연결 테스트 */}
      <input type="text" onChange={handleInputChange} title="입력" />
      <Button
        variant="contained"
        pattern="text-only"
        text="등록"
        disabled={isInputEmpty}
      />
      <Button
        variant="contained"
        pattern="text-only"
        text="저장"
        icon="edit"
        disabled={isInputEmpty}
      />

      {/* 텍스트 버튼 - 배경 X */}
      <Button variant="ghost" pattern="text-only" text="기록 전체 삭제" />

      {/* 텍스트 버튼 - 배경 O*/}
      <Button variant="contained" pattern="text-only" text="취소" />
      <Button variant="contained" pattern="text-only" text="저장" />
      <Button variant="contained" pattern="text-only" text="등록" />
      <Button variant="contained" pattern="text-only" text="삭제" />

      {/* x 닫기 */}
      <Button
        variant="contained"
        pattern="icon-text"
        text="닫기"
        icon="close"
      />

      {/* 아이콘 버튼 */}
      <Button variant="ghost" pattern="icon-only" icon="edit" />
      <Button variant="ghost" pattern="icon-only" icon="close" />
      <Button variant="ghost" pattern="icon-only" icon="history" />
      <Button variant="ghost" pattern="icon-only" icon="plus" />

      {/*  카드 */}
      {/* <Card
          mode="default"
          key={item.taskId}
          title={item.title}
          contents={item.contents}
          platform={item.platform}
        ></Card> */}
    </>
  );
}
