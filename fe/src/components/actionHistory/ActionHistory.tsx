import styled, { keyframes } from 'styled-components';
import { Button } from '../buttons/Button';
import { ActionList } from './ActionList';

type StyleProps = {
  isVisible: boolean;
};

type ActionHistoryProps = {
  onClose: () => void;
  onAnimationEnd: () => void;
  isVisible: boolean;
};

export const ActionHistory: React.FC<ActionHistoryProps> = ({
  onClose,
  onAnimationEnd,
  isVisible,
}) => {
  return (
    <ActionHistoryLayout isVisible={isVisible} onAnimationEnd={onAnimationEnd}>
      <div className="titleArea">
        <p className="titleText">사용자 활동 기록</p>
        <Button
          variant="ghost"
          pattern="icon-text"
          text="닫기"
          icon="close"
          onClick={onClose}
        />
      </div>
      <ActionList />
    </ActionHistoryLayout>
  );
};

const ActionHistoryLayout = styled.div<StyleProps>`
  animation: ${(props) => (props.isVisible ? slideIn : slideOut)} 0.5s;
  animation-fill-mode: forwards;
  position: absolute;
  top: 64px;
  right: 56px;
  display: inline-flex;
  flex-direction: column;
  align-items: flex-start;
  background-color: ${({ theme: { colors } }) => colors.surfaceDefault};
  box-shadow: ${({ theme: { shadows } }) => shadows.floating};
  border-radius: ${({ theme: { border } }) => border.radius16};
  padding: 8px;

  max-height: 680px;
  overflow-y: auto;

  .titleArea {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 350px;
    padding: 8px 8px 8px 16px;
  }

  .titleText {
    font: ${({ theme: { fonts } }) => fonts.displayB16};
    color: ${({ theme: { colors } }) => colors.textStrong};
    display: flex;
    align-items: center;
    justify-content: center;
  }
`;

const slideIn = keyframes`
  from {
    transform: translateX(200px);
    opacity: 0;
  }

  to {
    transform: translateX(0);
    opacity: 1;
  }
`;

const slideOut = keyframes`
  from {
    transform: translateX(0);
    opacity: 1;
  }

  to {
    transform: translateX(200px);
    opacity: 0;
  }
`;
