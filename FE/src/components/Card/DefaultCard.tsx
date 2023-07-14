import { colors } from "../../constants/colors";
import { IconButton } from "../Button/IconButton";
import { Txt } from "../Txt";
import { getUserDevice } from "../../utils/getUserDevice";
import { shadow } from "../../constants/shadow";

export function DefaultCard({
  id,
  cardTitle,
  cardContent,
  removeCard,
}: {
  id: number;
  cardTitle: string;
  cardContent: string;
  removeCard(key: number, cardTitle: string): void;
}) {
  const handleRemoveCard = () => {
    removeCard(id, cardTitle);
  };

  return (
    <div
      css={{
        display: "flex",
        flexDirection: "column",
        width: "268px",
        padding: "16px",
        gap: "16px",
        backgroundColor: colors.surfaceDefault,
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
              <Txt typography="displayBold14" color={colors.textStrong}>
                {cardTitle}
              </Txt>
            </div>
            <div>
              <Txt typography="displayMedium14" color={colors.textDefault}>
                {cardContent}
              </Txt>
            </div>
          </div>
          <div>
            <Txt typography="displayMedium12" color={colors.textWeak}>
              {`author by ${getUserDevice()}`}
            </Txt>
          </div>
        </div>
        <div>
          <IconButton
            type="close"
            width="24px"
            height="24px"
            color={colors.textWeak}
            onClick={handleRemoveCard}
          />
          <IconButton
            type="edit"
            width="24px"
            height="24px"
            color={colors.textWeak}
          />
        </div>
      </div>
    </div>
  );
}
