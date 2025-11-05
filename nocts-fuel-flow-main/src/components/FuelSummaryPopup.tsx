import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { X } from "lucide-react";

interface Props {
  visible: boolean;
  amount: number;
  fuelLiters: number;
  subsidyLiters: number;
  onClose: () => void;
}

const FuelSummaryPopup = ({ visible, amount, fuelLiters, subsidyLiters, onClose }: Props) => {
  if (!visible) return null;

  return (
    <div className="fixed bottom-6 right-6 z-50 animate-in fade-in slide-in-from-bottom-4 duration-300">
      <Card className="p-4 shadow-xl bg-surface-elevated border border-border rounded-xl w-72 relative">
        
        {/* Close Button */}
        <Button
          variant="ghost"
          size="icon"
          className="absolute top-2 right-2"
          onClick={onClose}
        >
          <X className="h-4 w-4" />
        </Button>

        <h3 className="font-semibold text-lg text-foreground mb-3">
          Fuel Summary
        </h3>

        <div className="space-y-1 text-sm">
          <p className="text-muted-foreground">
            Amount: <span className="text-foreground font-semibold">RM {amount.toFixed(2)}</span>
          </p>

          <p className="text-muted-foreground">
            Fuel Dispensed: <span className="text-foreground font-semibold">{fuelLiters.toFixed(2)} ℓ</span>
          </p>

          <p className="text-muted-foreground">
            Subsidy: <span className="text-foreground font-semibold">{subsidyLiters.toFixed(2)} ℓ</span>
          </p>
        </div>
      </Card>
    </div>
  );
};

export default FuelSummaryPopup;
