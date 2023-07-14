import { useState } from "react";
import { colors } from "../../constants/colors";
import { Button } from "../Button/Button";
import { shadow } from "../../constants/shadow";

export function AddCard({
  closeAddCard,
  addNewCard,
}: {
  closeAddCard(): void;
  addNewCard(inputTitle: string, inputContent: string): void;
}) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const handleTitleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(e.target.value);
  };

  const handleContentChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setContent(e.target.value);
  };

  const clickButton = () => {
    addNewCard(title, content);
    closeAddCard();
  };

  const isButtonDisabled = title.trim() === "" || content.trim() === "";

  return (
    <form
      css={{
        display: "flex",
        flexDirection: "column",
        width: "268px",
        padding: "16px",
        gap: "16px",
        backgroundColor: `${colors.surfaceDefault}`,
        borderRadius: "8px",
        boxShadow: shadow.normal,
      }}>
      <div
        css={{
          display: "flex",
          gap: "4px",
        }}>
        <div
          css={{
            display: "flex",
            flexDirection: "column",
            width: "240px",
            gap: "16px",
          }}>
          <div
            css={{
              display: "flex",
              flexDirection: "column",
              gap: "8px",
            }}>
            <div>
              <input
                css={{
                  "width": "100%",
                  "border": "none",
                  "fontSize": "14px",
                  "fontWeight": 700,
                  "color": colors.textStrong,
                  "::placeholder": {
                    color: colors.textStrong,
                  },
                  ":focus": {
                    outline: 0,
                  },
                }}
                placeholder="제목을 입력해주세요"
                type="text"
                value={title}
                onChange={handleTitleChange}
              />
            </div>
            <div>
              <input
                css={{
                  "width": "100%",
                  "border": "none",
                  "fontSize": "14px",
                  "fontWeight": 500,
                  "color": colors.textDefault,
                  "::placeholder": {
                    color: colors.textDefault,
                  },
                  ":focus": {
                    outline: 0,
                  },
                  "whiteSpace": "pre-wrap",
                }}
                placeholder="내용을 입력해주세요"
                type="text"
                value={content}
                onChange={handleContentChange}
              />
            </div>
          </div>
        </div>
      </div>
      <div
        css={{
          display: "flex",
          gap: "8px",
        }}>
        <Button
          text="취소"
          width="132px"
          height="32px"
          color={`${colors.textDefault}`}
          backgroundColor={`${colors.surfaceAlt}`}
          onClick={closeAddCard}
        />
        <Button
          text="등록"
          width="132px"
          height="32px"
          color={`${colors.textWhiteDefault}`}
          backgroundColor={`${colors.surfaceBrand}`}
          onClick={clickButton}
          disabled={isButtonDisabled}
        />
      </div>
    </form>
  );
}
