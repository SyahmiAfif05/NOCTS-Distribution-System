import { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { toast } from "sonner";
import { Fuel, Gauge, ArrowLeft, CheckCircle2 } from "lucide-react";

const FuelActivation = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const userId = location.state?.userId;
  const eligibility = location.state?.eligibility;

  const [selectedPump, setSelectedPump] = useState<number | null>(null);
  const [fuelAmount, setFuelAmount] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Redirect if no userId
  if (!userId) {
    navigate("/");
    return null;
  }

  const pumps = [1, 2, 3, 4, 5];

  const handleSubmit = async () => {
    if (!selectedPump) {
      toast.error("Please select a pump number");
      return;
    }

    const amount = parseFloat(fuelAmount);
    if (!fuelAmount || isNaN(amount) || amount <= 0) {
      toast.error("Please enter a valid amount");
      return;
    }

    if (amount > 500) {
      toast.error("Maximum amount is RM 500");
      return;
    }

    setIsSubmitting(true);

    try {
      // Simulate API call
      const payload = {
        userId,
        pumpNumber: selectedPump,
        fuelAmount: amount,
        timestamp: new Date().toISOString(),
      };

      console.log("Sending fuel activation request:", payload);

      // Simulated API call to backend
      // Replace with actual endpoint: POST /api/fuel-activation
      await new Promise((resolve) => setTimeout(resolve, 1500));

      // Success
      toast.success("Fuel dispenser activated!", {
        description: `Pump ${selectedPump} • RM ${amount.toFixed(2)} authorized`,
        duration: 4000,
      });

      // Reset and return to scan page after delay
      setTimeout(() => {
        navigate("/");
      }, 2000);
    } catch (error) {
      toast.error("Activation failed", {
        description: "Please try again or contact support",
      });
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-background p-6">
      <Card className="w-full max-w-3xl p-12 shadow-kiosk-lg">
        <div className="space-y-8">
          {/* Header */}
          <div className="space-y-4">
            <Button
              variant="ghost"
              onClick={() => navigate("/")}
              className="mb-4"
              disabled={isSubmitting}
            >
              <ArrowLeft className="h-5 w-5 mr-2" />
              Back to Scan
            </Button>
            
            <div className="flex items-center justify-between gap-4">
              <div className="flex items-center gap-4">
                <div className="p-3 bg-success/10 rounded-full">
                  <Fuel className="h-12 w-12 text-success" />
                </div>
                <div>
                  <h1 className="text-3xl font-bold text-foreground">Fuel Activation</h1>
                  <p className="text-muted-foreground">User ID: <span className="font-mono font-semibold">{userId}</span></p>
                </div>
              </div>
              {eligibility && (
                <Badge className={`px-4 py-2 text-base ${
                  eligibility === "eligible" 
                    ? "bg-success text-success-foreground" 
                    : "bg-muted text-muted-foreground"
                }`}>
                  {eligibility === "eligible" ? "Subsidy Eligible" : "Not Eligible"}
                </Badge>
              )}
            </div>
          </div>

          {/* Pump Selection */}
          <div className="space-y-4">
            <div className="flex items-center gap-3 text-foreground">
              <Gauge className="h-6 w-6" />
              <label className="text-xl font-medium">Select Pump Number</label>
            </div>
            
            <div className="grid grid-cols-5 gap-4">
              {pumps.map((pump) => (
                <Button
                  key={pump}
                  onClick={() => setSelectedPump(pump)}
                  variant={selectedPump === pump ? "default" : "outline"}
                  size="lg"
                  className={`h-24 text-2xl font-bold transition-all ${
                    selectedPump === pump 
                      ? "bg-primary ring-4 ring-primary/20 scale-105" 
                      : "hover:scale-105"
                  }`}
                  disabled={isSubmitting}
                >
                  {pump}
                </Button>
              ))}
            </div>
          </div>

          {/* Fuel Amount Input */}
          <div className="space-y-4">
            <div className="flex items-center gap-3 text-foreground">
              <Fuel className="h-6 w-6" />
              <label className="text-xl font-medium">Amount (Malaysian Ringgit)</label>
            </div>
            
            <div className="relative">
              <span className="absolute left-6 top-1/2 -translate-y-1/2 text-2xl text-muted-foreground font-semibold">
                RM
              </span>
              <Input
                type="number"
                value={fuelAmount}
                onChange={(e) => setFuelAmount(e.target.value)}
                placeholder="0.00"
                min="0"
                max="500"
                step="5"
                className="h-20 text-3xl text-center font-bold px-20"
                disabled={isSubmitting}
              />
            </div>

            <div className="flex gap-3">
              {[20, 30, 50, 100].map((preset) => (
                <Button
                  key={preset}
                  onClick={() => setFuelAmount(preset.toString())}
                  variant="secondary"
                  size="sm"
                  className="flex-1"
                  disabled={isSubmitting}
                >
                  RM {preset}
                </Button>
              ))}
            </div>
          </div>

          {/* Submit Button */}
          <Button
            onClick={handleSubmit}
            disabled={!selectedPump || !fuelAmount || isSubmitting}
            size="lg"
            className="w-full h-20 text-2xl bg-success hover:bg-success/90 disabled:opacity-50"
          >
            {isSubmitting ? (
              <>
                <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-white mr-3" />
                Activating...
              </>
            ) : (
              <>
                <CheckCircle2 className="h-7 w-7 mr-3" />
                Activate Dispenser
              </>
            )}
          </Button>

          {/* Summary */}
          {selectedPump && fuelAmount && (
            <div className="p-6 bg-accent/10 rounded-lg border-2 border-accent/30">
              <div className="text-center space-y-2">
                <p className="text-sm text-muted-foreground font-medium">ACTIVATION SUMMARY</p>
                <p className="text-2xl font-bold text-foreground">
                  Pump {selectedPump} • RM {parseFloat(fuelAmount).toFixed(2)}
                </p>
                {eligibility === "eligible" && (
                  <div className="mt-3 pt-3 border-t border-accent/30">
                    <p className="text-sm text-muted-foreground">Subsidy Benefit</p>
                    <p className="text-xl font-semibold text-success">
                      +{((parseFloat(fuelAmount) / 1.75) - (parseFloat(fuelAmount) / 2.05)).toFixed(2)} Liters
                    </p>
                    <p className="text-xs text-muted-foreground mt-1">
                      (Getting {(parseFloat(fuelAmount) / 1.75).toFixed(2)}L instead of {(parseFloat(fuelAmount) / 2.05).toFixed(2)}L)
                    </p>
                  </div>
                )}
              </div>
            </div>
          )}
        </div>
      </Card>
    </div>
  );
};

export default FuelActivation;
