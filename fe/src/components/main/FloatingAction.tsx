import React, { useState } from 'react';
import styled, { css, keyframes } from 'styled-components';
import { Button } from '../buttons/Button';
// 개발중
// todo
// 컬럼 삭제 버튼과 - 컬럼 타이틀의 x 버튼 연동
// 애니메이션

type FABStyledProps = {
  mode: string;
};

type FloatingActionProps = {
  onNewColumn?: () => void;
};

export const FloatingActionBtn: React.FC<FloatingActionProps> = ({
  onNewColumn,
}) => {
  const [isSelectMode, setIsSelectMode] = useState(false);

  const changeModeHandler = () => {
    setIsSelectMode((prev) => !prev);
  };

  const handleSubmit = async () => {
    const response = await fetch(`/api/process`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        processName: '새 리스트',
      }),
    });

    const responseData = await response.json();
    console.log(responseData);
    if (onNewColumn) {
      onNewColumn();
    }
  };

  return (
    <FloatingActionBtnLayout mode={isSelectMode ? 'true' : 'false'}>
      {isSelectMode && (
        <div className="selectMode">
          <Button
            variant="contained"
            pattern="text-only"
            text="컬럼 추가"
            onClick={handleSubmit}
          />
          <Button variant="contained" pattern="text-only" text="컬럼 삭제" />
        </div>
      )}
      <div className="fabBtn" onClick={changeModeHandler}>
        <Button variant="ghost" pattern="icon-only" icon="plus" />
      </div>
    </FloatingActionBtnLayout>
  );
};

const FloatingActionBtnLayout = styled.div<FABStyledProps>`
  position: fixed;
  bottom: 16px;
  right: 16px;

  display: flex;
  cursor: pointer;
  box-shadow: ${({ theme: { shadows } }) => shadows.up.boxShadow};
  backdrop-filter: ${({ theme: { shadows } }) => shadows.up.backdropFilter};

  ${(props) =>
    props.mode
      ? css`
          flex-direction: column;
          justify-content: center;
          background-color: ${({ theme: { colors } }) => colors.surfaceDefault};
          border-radius: 16px 16px 28px 16px;
        `
      : css`
          width: 56px;
          height: 56px;
          background-color: ${({ theme: { colors } }) => colors.surfaceBrand};
          border-radius: ${({ theme: { border } }) => border.radius50};
          justify-content: center;
          align-items: center;
        `}

  ${(props) =>
    props.mode
      ? css`
          .fabBtn button {
            animation: ${tilt} 0.5s ease-in-out;
            animation-fill-mode: forwards;
          }
          .selectMode {
            animation: ${expand} 0.5s ease-in-out;
          }
          .selectMode.animating-out {
            animation: ${shrink} 0.5s ease-in-out;
          }
          .selectMode button {
            animation: ${fadeIn} 0.5s ease-in-out;
          }
        `
      : css`
          // .fabBtn button {
          //   animation: ${normal} 0.5s ease-in-out;
          //   animation-fill-mode: forwards;
          // }
          .selectMode {
            animation: ${shrink} 0.5s ease-in-out;
          }
          .selectMode button {
            animation: ${fadeOut} 0.5s ease-in-out;
          }
        `}

  .fabBtn {
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;

    bottom: 0px;
    right: 0px;

    width: 56px;
    height: 56px;
    background-color: ${({ theme: { colors } }) => colors.surfaceBrand};
    border-radius: ${({ theme: { border } }) => border.radius50};
  }

  .fabBtn button {
    transform: rotate(0deg);
  }
  .fabBtn svg path {
    fill: ${({ theme: { colors } }) => colors.surfaceDefault};
  }

  .selectMode {
    display: flex;
    flex-direction: column;
    animation: 0.5s ease-in-out forwards;
    animation-fill-mode: forwards;
    height: 164px;
    width: 164px;
    padding: 16px;
    gap: 8px;
  }

  .selectMode button {
    animation: 0.5s ease-in-out forwards;
    animation-fill-mode: forwards;
  }
`;
const expand = keyframes`
  0% {
    height: 20px;
    width: 20px;
    opacity: 0;
  }
  100% {
    height: 164px; 
    width:  164px; 
    opacity: 1;
  }
`;

const shrink = keyframes`
  0% {
    height: 164px;
    width:  164px; 
    opacity: 1;
  }
  100% {
    height: 20px;
    width: 20px;
    opacity: 0;
  }
`;

const fadeIn = keyframes`
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
`;

const fadeOut = keyframes`
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
`;

const tilt = keyframes`
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(45deg)
    
  }
`;

const normal = keyframes`
  0% {
    transform: rotate(45deg)
  }
  100% {
    transform: rotate(0deg);
  }
`;
