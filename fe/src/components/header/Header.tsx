import { useState } from 'react';
import styled from 'styled-components';
import { Button } from '../buttons/Button';
import { ActionHistory } from '../actionHistory/ActionHistory';
import { HeaderTitle } from './HeaderTitle';

export const Header = () => {
  const [isVisible, setIsVisible] = useState(false);
  const [isAnimating, setIsAnimating] = useState(false);

  const onMove = () => {
    setIsAnimating(true);
    setIsVisible((prevVisible) => !prevVisible);
  };

  const onAnimationEnd = () => {
    if (!isVisible) {
      setIsAnimating(false);
    }
  };

  return (
    <HeaderLayout>
      <HeaderTitle />
      <Button
        variant="ghost"
        pattern="icon-only"
        icon="history"
        onClick={onMove}
      />
      {isAnimating && (
        <ActionHistory
          isVisible={isVisible}
          onClose={onMove}
          onAnimationEnd={onAnimationEnd}
        />
      )}
    </HeaderLayout>
  );
};

const HeaderLayout = styled.div`
  display: flex;
  height: 64px;
  padding: 18px 80px 17px 80px;
  justify-content: space-between;
  align-items: center;
  background-color: ${(props) => props.theme.colors.surfaceAlt};
`;
