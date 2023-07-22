import React from 'react';
import styled, { css } from 'styled-components';
import { Close } from './icon/Close';
import { Plus } from './icon/Plus';
import { Edit } from './icon/Edit';
import { History } from './icon/History';


type ButtonProps = {
  variant: 'contained' | 'ghost';
  pattern: 'icon-only' | 'text-only' | 'icon-text';
  text?: string;
  icon?: 'close' | 'edit' | 'history' | 'plus';
  disabled?: boolean;
  onClick?: () => void;
};


export const Button: React.FC<ButtonProps> = ({
  variant,
  pattern,
  text,
  icon,
  disabled,
  onClick,
}) => {
  const renderIcon = () => {
    switch (icon) {
      case 'close':
        return <Close />;
      case 'edit':
        return <Edit />;
      case 'history':
        return <History />;
      case 'plus':
        return <Plus />;
      default:
        return null;
    }
  };

  return (
    <ButtonLayout
      variant={variant}
      pattern={pattern}
      text={text}
      icon={icon}
      disabled={disabled}
      onClick={onClick}
    >
      {(pattern === 'icon-text' || pattern === 'icon-only') && renderIcon()}
      {pattern !== 'icon-only' && text}
    </ButtonLayout>
  );
};

const ButtonLayout = styled.button<ButtonProps>`
  display: flex;
  padding: 0px 8px;
  justify-content: ${({ text }) =>
    text === '기록 전체 삭제' ? 'flex-end' : 'center'};
  align-items: center;
  border-radius: ${({ theme: { border } }) => border.radius8};
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ text, theme: { colors } }) =>
    text === '기록 전체 삭제'
      ? colors.textDanger
      : text === '취소' ||
        text === '닫기' ||
        text === '컬럼 삭제' ||
        text === '컬럼 추가'
      ? colors.textDefault
      : colors.textWhiteDefault};

  background-color: ${({ variant, text, theme: { colors } }) =>
    variant === 'contained'
      ? text === '취소'
        ? colors.surfaceAlt
        : text === '저장' || text === '등록'
        ? colors.surfaceBrand
        : text === '삭제'
        ? colors.surfaceDanger
        : colors.surfaceAlt
      : 'transparent'};

  width: ${({ variant, pattern }) =>
    variant === 'contained' && pattern === 'text-only' ? '132px' : 'auto'};

  height: ${({ pattern }) => (pattern !== 'icon-only' ? '32px' : 'auto')};

  img {
    width: ${({ pattern }) => (pattern === 'icon-text' ? '16px' : '24px')};
    margin-right: ${({ pattern }) => pattern === 'icon-text' && '4px'};
  }

  opacity: ${({ disabled }) => (disabled ? 0.3 : 1)};

  ${({ variant, disabled }) =>
    (variant === 'contained' || variant === 'ghost') &&
    !disabled &&
    `
    &:hover {
      opacity: 0.8;
    }
  `}

  ${({ pattern, icon, theme: { colors } }) =>
    pattern === 'icon-only' &&
    icon === 'close' &&
    css`
      &:hover {
        svg path {
          transition: fill 0.5s ease-in-out;
          fill: ${colors.surfaceDanger};
        }
      }
    `}
`;
